package com.example.nicholasburns.mealmaprestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/*Main activity for viewing and navigating restaurants
 */
public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        RestaurantHandler restaurantHandler = MapsActivity.getRestaurantHandler();

        String [] foodItems = new String[restaurantHandler.getMenu().size()];

        TextView restaurantTitle = (TextView) findViewById(R.id.restaurant_name_title);

        restaurantTitle.setText(restaurantHandler.getName());

        for(int i = 0; i < restaurantHandler.getMenu().size(); i++){
            foodItems[i] = restaurantHandler.getMenu().get(i).name;
        }



        ListAdapter foodAdapter = new RestaurantAdapter(this, foodItems);

        ListView foodListView = (ListView) findViewById(R.id.food_item_list_view);

        foodListView.setAdapter(foodAdapter);

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //navigates to food description page if a food item is clicked
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                foodItemClicked(parent, view, position, id);
            }
        });
    }

    //navigates to food description page if a food item is clicked
    private void foodItemClicked(AdapterView<?> parent, View view, int position, long id){
        Intent foodIntent = new Intent(this, FoodItemPage.class);

        RestaurantHandler restaurantHandler = MapsActivity.getRestaurantHandler();

        final int result = 1;

        int foodPicId;
        String foodDes;

        foodPicId = restaurantHandler.getMenu().get(position).imageId;

        foodDes = restaurantHandler.getMenu().get(position).description;

        //send the id of the food that was clicked
        foodIntent.putExtra("clickedFood", String.valueOf(parent.getItemAtPosition(position)));

        //send the id of that food's picture
        foodIntent.putExtra("foodPic", foodPicId);

        //send the description of the food
        foodIntent.putExtra("description", foodDes);

        startActivityForResult(foodIntent, result);
    }
}
