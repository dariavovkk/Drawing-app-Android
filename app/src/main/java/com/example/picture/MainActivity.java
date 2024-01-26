package com.example.picture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private MySurfaceView drawingSurface;
    Button redButton;
    Button yellowButton;
    Button blueButton;
    Button greenButton;
    Button clearButton;
    Button saveButton;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int SAVE_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawingSurface = findViewById(R.id.surface_view);
        drawingSurface.setColor(getResources().getColor(R.color.red));

        redButton = findViewById(R.id.red_button);
        yellowButton = findViewById(R.id.yellow_button);
        blueButton = findViewById(R.id.blue_button);
        greenButton = findViewById(R.id.green_button);
        clearButton = findViewById(R.id.clear_button);
        saveButton = findViewById(R.id.save_button);

        redButton.setBackgroundColor(getResources().getColor(R.color.red));
        yellowButton.setBackgroundColor(getResources().getColor(R.color.yellow));
        blueButton.setBackgroundColor(getResources().getColor(R.color.blue));
        greenButton.setBackgroundColor(getResources().getColor(R.color.green));
        clearButton.setBackgroundColor(getResources().getColor(R.color.gray));

        redButton.setOnClickListener(v -> drawingSurface.setColor(getResources().getColor(R.color.red)));
        yellowButton.setOnClickListener(v -> drawingSurface.setColor(getResources().getColor(R.color.yellow)));
        blueButton.setOnClickListener(v -> drawingSurface.setColor(getResources().getColor(R.color.blue)));
        greenButton.setOnClickListener(v -> drawingSurface.setColor(getResources().getColor(R.color.green)));
        clearButton.setOnClickListener(v -> drawingSurface.clear());
        saveButton.setOnClickListener(v -> saveDrawing());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    private void saveDrawing() {
        Bitmap drawingBitmap = drawingSurface.getDrawingBitmap();

        String fileName = "drawing.png";
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(downloadsDir, fileName);

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            drawingBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            Toast.makeText(MainActivity.this, "File saved: " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Failed to save file", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_saved_items) {
            // Obsługa kliknięcia przycisku "Listy rysunków"
            Toast.makeText(this, "Open drawings list", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPause() {
        super.onPause();
        drawingSurface.stopDrawing();
    }

    @Override
    protected void onResume() {
        super.onResume();
        drawingSurface.startDrawing();
    }
}