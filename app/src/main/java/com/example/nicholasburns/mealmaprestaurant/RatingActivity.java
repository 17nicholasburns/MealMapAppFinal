package com.example.nicholasburns.mealmaprestaurant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

/**Activity that handles the rating of a food item
 * Created by Taquito on 5/25/2016.
 */
public class RatingActivity extends Activity{
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_activity_layout);

        final Intent ratingIntent = getIntent();

        context = this;

        ListView ratingListView = (ListView) findViewById(R.id.food_item_rating_list_view);

        String [] ratings = new String[5];

        ratings[0] = "1";
        ratings[1] = "2";
        ratings[2] = "3";
        ratings[3] = "4";
        ratings[4] = "5";

        RatingAdapter ratingAdapter = new RatingAdapter(this, ratings);

        ratingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                  @Override
                                                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                      int [] ratingIndexArray = new int[1];
                                                      ratingIndexArray[0] = position;
                                                      ratingIntent.putExtra("ratingIndex", ratingIndexArray);
                                                      setResult(RESULT_OK, ratingIntent);
                                                      finish();
                                                  }
                                              });

        ratingListView.setAdapter(ratingAdapter);
    }

}
