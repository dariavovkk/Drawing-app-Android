package com.example.picture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class DrawingListFragment extends Fragment {
    private ListView drawingListView;
    private ArrayAdapter<String> adapter;
    private List<String> drawingList;
    private OnDrawingSelectedListener onDrawingSelectedListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inicjalizacja listy rysunków
        drawingList = new ArrayList<>();
        drawingList.add("Drawing 1");
        drawingList.add("Drawing 2");
        drawingList.add("Drawing 3");
        // Inicjalizacja adaptera listy
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, drawingList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawing_list, container, false);
        drawingListView = view.findViewById(R.id.drawing_list_view);
        drawingListView.setAdapter(adapter);
        drawingListView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedDrawing = drawingList.get(position);
            if (onDrawingSelectedListener != null) {
                onDrawingSelectedListener.onDrawingSelected(selectedDrawing);
            }
        });
        return view;
    }

    public void setOnDrawingSelectedListener(OnDrawingSelectedListener listener) {
        this.onDrawingSelectedListener = listener;
    }

    public interface OnDrawingSelectedListener {
        void onDrawingSelected(String drawingPath);
    }
}
