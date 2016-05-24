package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.List;

/**Adapts the listview of genres for the filter sidebar
 * Created by 17nicholasburns on 3/29/2016.
 */
public class GenreAdapter extends ArrayAdapter <String> {
    private List genreList;
    public GenreAdapter(Context context, String [] values) {
        super(context, R.layout.genre_list_item_view, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater genreInflater = LayoutInflater.from(getContext());

        View genreView = genreInflater.inflate(R.layout.genre_list_item_view, parent, false);

        String genreitem = getItem(position);

        CheckedTextView genreTitle = (CheckedTextView) genreView.findViewById(R.id.genre_list_item_text_view);
        genreTitle.setText(genreitem);



        return genreView;
    }
}
