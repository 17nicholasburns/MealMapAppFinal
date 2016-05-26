package com.example.nicholasburns.mealmaprestaurant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**Activity that controls the operation of Genre filters
 * Created by 17nicholasburns on 3/16/2016.
 */
public class GenresActivity extends Activity {
    Context context;
    private List<String> listOfCheckedGenres = new ArrayList<String>();

    @Override
    //Determines functions after the GenreActivity Activity has been created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);

        final Intent sideBarIntent = getIntent();

        final String [] genreList;

        context = this;

        genreList = sideBarIntent.getExtras().getStringArray("genreList");

        TextView filterByGenre = (TextView) findViewById(R.id.genre_activity_title);

        ListView genreListView = (ListView) findViewById(R.id.genre_filter_list_view);

        genreListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        genreListView.setOnItemClickListener(new GenreFilterClickListener());

        Button genreFilterButton = (Button) findViewById(R.id.genre_filter_submit_button);

        //OnClickListener that returns the genres that should appear on the map
        genreFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listOfCheckedGenres.size() > 0) {
                    String lst = "";
                    String[] arr = new String[listOfCheckedGenres.size()];
                    for (int i = 0;i < listOfCheckedGenres.size();i++) {
                        lst = lst + listOfCheckedGenres.get(i) + " ";
                        arr[i] = listOfCheckedGenres.get(i);
                    }

                    if (arr.length > 0) {
                        sideBarIntent.putExtra("listOfCheckedGenres", arr);
                        setResult(RESULT_OK, sideBarIntent);
                        finish();
                    }
                    else{
                        setResult(RESULT_OK, sideBarIntent);
                        finish();
                    }
                }
            }
        });

        GenreAdapter genreAdapter = new GenreAdapter(this, genreList);

        genreListView.setAdapter(genreAdapter);
    }


    /** Creates OnItemClickListener for the genre filters
     * Created by 17nicholasburns on 4/28/2016.
     */
    public class GenreFilterClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CheckedTextView checkedGenreTextView = (CheckedTextView) view;

            if(checkedGenreTextView.isChecked()){
                listOfCheckedGenres.add(String.valueOf(((CheckedTextView) view).getText()));
            }
            else{
                for(int i = 0; i < listOfCheckedGenres.size(); i++) {
                    if (listOfCheckedGenres.get(i).equalsIgnoreCase(String.valueOf(((CheckedTextView) view).getText()))) {
                        listOfCheckedGenres.remove(i);
                    }
                }
            }
        }
    }
}


