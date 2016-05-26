package com.example.nicholasburns.mealmaprestaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**Activity for the page that opens when you click a food item
 * Created by 17nicholasburns on 2/29/2016.
 */
public class FoodItemPage extends Activity {
    private String foodName;
    private String restaurantName;
    private String genreName;
    private int rating;

    @Override
    //tasks that will be completed when the activity is created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.specific_food_layout);

        Intent foodWasCalled = getIntent();

        foodName = foodWasCalled.getExtras().getString("clickedFood");
        restaurantName = foodWasCalled.getExtras().getString("restaurantName");
        genreName = foodWasCalled.getExtras().getString("genreName");

        int foodPicId = foodWasCalled.getExtras().getInt("foodPic");

        String FDesString = foodWasCalled.getExtras().getString("description");

        if(!MapsActivity.rCC.checkIfChanged(foodName)) {
            rating = RatingReader.getRating(foodName, genreName, this);
        }
        else{
            rating = MapsActivity.rCC.getRating(foodName);
        }



        //create image views for stars
        ImageView s1 = (ImageView) findViewById(R.id.star_one);
        ImageView s2 = (ImageView) findViewById(R.id.star_two);
        ImageView s3 = (ImageView) findViewById(R.id.star_three);
        ImageView s4 = (ImageView) findViewById(R.id.star_four);
        ImageView s5 = (ImageView) findViewById(R.id.star_five);
        //title of the food
        TextView foodTitle = (TextView) findViewById(R.id.specific_food_title);

        //image of the food
        ImageView foodImg = (ImageView) findViewById(R.id.image_specific_food);


        //decide the rating of the food
        switch (rating) {
            case 1:
                s1.setImageResource(R.drawable.fullstar);
                s2.setImageResource(R.drawable.emptystar);
                s3.setImageResource(R.drawable.emptystar);
                s4.setImageResource(R.drawable.emptystar);
                s5.setImageResource(R.drawable.emptystar);
                break;
            case 2:
                s1.setImageResource(R.drawable.fullstar);
                s2.setImageResource(R.drawable.fullstar);
                s3.setImageResource(R.drawable.emptystar);
                s4.setImageResource(R.drawable.emptystar);
                s5.setImageResource(R.drawable.emptystar);
                break;
            case 3:
                s1.setImageResource(R.drawable.fullstar);
                s2.setImageResource(R.drawable.fullstar);
                s3.setImageResource(R.drawable.fullstar);
                s4.setImageResource(R.drawable.emptystar);
                s5.setImageResource(R.drawable.emptystar);
                break;
            case 4:
                s1.setImageResource(R.drawable.fullstar);
                s2.setImageResource(R.drawable.fullstar);
                s3.setImageResource(R.drawable.fullstar);
                s4.setImageResource(R.drawable.fullstar);
                s5.setImageResource(R.drawable.emptystar);
                break;
            case 5:
                s1.setImageResource(R.drawable.fullstar);
                s2.setImageResource(R.drawable.fullstar);
                s3.setImageResource(R.drawable.fullstar);
                s4.setImageResource(R.drawable.fullstar);
                s5.setImageResource(R.drawable.fullstar);
                break;
            default:
                s1.setImageResource(R.drawable.emptystar);
                s2.setImageResource(R.drawable.emptystar);
                s3.setImageResource(R.drawable.emptystar);
                s4.setImageResource(R.drawable.emptystar);
                s5.setImageResource(R.drawable.emptystar);
                break;
        }

        //description of the food
        TextView foodDes = (TextView) findViewById(R.id.specific_food_description);

        foodTitle.setText(foodName);

        foodImg.setImageResource(foodPicId);

        foodDes.setText(FDesString);
    }

    @Override
    //changes the rating of the food item, updates the ratingChangeChecker
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int newRating = data.getIntArrayExtra("ratingIndex")[0];
                rating = RatingUpdater.changeRating(foodName, genreName, newRating+1, this);
                MapsActivity.rCC.addNewChange(foodName, newRating+1, 1);
            }
        }
    }

    //finds the rating of this foodItem
    private int getRating(String foodName) {
        return 1;
    }

    //opens the activity when the button to rate a food item is clicked
    public void ratingButtonOnClick(View view) {
        Intent ratingIntent = new Intent(this, RatingActivity.class);
        final int RESULT = 1;

        ratingIntent.putExtra("foodName", foodName);

        ratingIntent.putExtra("restaurantName", restaurantName);

        ratingIntent.putExtra("genreName", genreName);

        startActivityForResult(ratingIntent, RESULT);
    }

}