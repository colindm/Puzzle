package com.example.puzzle;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class PuzzleAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> numberList;

    public PuzzleAdapter(Context context, ArrayList<Integer> numberList) {
        this.context = context;
        this.numberList = numberList;
    }

    @Override
    public int getCount() {
        return numberList.size();
    }

    @Override
    public Object getItem(int position) {
        return numberList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            // If the view hasn't been created yet, inflate a new TextView
            textView = new TextView(context);
            textView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
            textView.setTextSize(40);
            textView.setPadding(10, 10, 10, 10);
        } else {
            // If the view already exists, reuse it
            textView = (TextView) convertView;
        }

        // Set the text of the TextView to the number, or an empty string if the tile is null
        Integer number = numberList.get(position);
        if (number != null) {
            textView.setText(Integer.toString(number));
        } else {
            textView.setText("");
        }

        // Set the background color to blue if the tile is in the correct position, or white otherwise
        if (number == null || number == position + 1) {
            textView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
        } else {
            textView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        }

        return textView;
    }
}