package com.example.puzzle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private Button resetButton;
    private PuzzleAdapter puzzleAdapter;
    private ArrayList<Integer> numberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to the GridView and Reset button
        gridView = findViewById(R.id.grid_view);
        resetButton = findViewById(R.id.reset_button);

        // Generate a list of numbers from 1 to 15
        numberList = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            numberList.add(i);
        }

        // Add a null to the end of the list to represent the empty tile
        numberList.add(null);

        // Shuffle the numbers
        Collections.shuffle(numberList);

        // Set up the GridView and PuzzleAdapter
        puzzleAdapter = new PuzzleAdapter(this, numberList);
        gridView.setAdapter(puzzleAdapter);

        // Set up the Reset button
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(numberList);
                puzzleAdapter.notifyDataSetChanged();
            }
        });

//        add onclick for grid
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            add onclick for grid
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "onItemClick: " + position);
                int empty = numberList.indexOf(null);
                if (position == empty - 1 || position == empty + 1 || position == empty - 4 || position == empty + 4) {
                    Collections.swap(numberList, position, empty);
                    puzzleAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    // Check if the puzzle is solved
    public boolean isSolved() {
        for (int i = 0; i < numberList.size() - 1; i++) {
            if (numberList.get(i) != i + 1) {
                return false;
            }
        }
        return true;
    }
}
