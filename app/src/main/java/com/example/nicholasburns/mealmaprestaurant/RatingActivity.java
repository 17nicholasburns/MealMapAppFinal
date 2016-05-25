package com.example.nicholasburns.mealmaprestaurant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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

        ImageView ratingImage = (ImageView) findViewById(R.id.rating_image);


    }
}
