package com.example.nicholasburns.mealmaprestaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**Activity for the page that opens when you click a food item
 * Created by 17nicholasburns on 2/29/2016.
 */
public class FoodItemPage extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.specific_food_layout);

        Intent foodWasCalled = getIntent();

        String foodName = foodWasCalled.getExtras().getString("clickedFood");

        int foodPicId = foodWasCalled.getExtras().getInt("foodPic");

        String FDesString = foodWasCalled.getExtras().getString("description");

        int rating = 4;

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
        switch (rating){
            case 1: s1.setImageResource(R.drawable.fullstar);
                s2.setImageResource(R.drawable.emptystar);
                s3.setImageResource(R.drawable.emptystar);
                s4.setImageResource(R.drawable.emptystar);
                s5.setImageResource(R.drawable.emptystar);
                break;
            case 2: s1.setImageResource(R.drawable.fullstar);
                s2.setImageResource(R.drawable.fullstar);
                s3.setImageResource(R.drawable.emptystar);
                s4.setImageResource(R.drawable.emptystar);
                s5.setImageResource(R.drawable.emptystar);
                break;
            case 3: s1.setImageResource(R.drawable.fullstar);
                s2.setImageResource(R.drawable.fullstar);
                s3.setImageResource(R.drawable.fullstar);
                s4.setImageResource(R.drawable.emptystar);
                s5.setImageResource(R.drawable.emptystar);
                break;
            case 4: s1.setImageResource(R.drawable.fullstar);
                s2.setImageResource(R.drawable.fullstar);
                s3.setImageResource(R.drawable.fullstar);
                s4.setImageResource(R.drawable.fullstar);
                s5.setImageResource(R.drawable.emptystar);
                break;
            case 5: s1.setImageResource(R.drawable.fullstar);
                s2.setImageResource(R.drawable.fullstar);
                s3.setImageResource(R.drawable.fullstar);
                s4.setImageResource(R.drawable.fullstar);
                s5.setImageResource(R.drawable.fullstar);
                break;
            default: s1.setImageResource(R.drawable.emptystar);
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

    private int getRating(String foodName) {
        return 1;
    }

    private void
}
