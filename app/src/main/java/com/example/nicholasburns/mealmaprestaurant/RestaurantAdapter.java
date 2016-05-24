package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**adapts the displayed screen to show the specific restaurant and it's list view
 * This adapter creates the list of food items available for a restaurant
 * Created by 17nicholasburns on 2/26/2016.
 */
public class RestaurantAdapter extends ArrayAdapter<String>{
    public RestaurantAdapter(Context context, String [] values) {
        super(context, R.layout.food_item_layout, values);
    }


    //create each individual food item in the list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater foodInflater = LayoutInflater.from(getContext());
        RestaurantHandler restaurantHandler = MapsActivity.getRestaurantHandler();

        View foodView = foodInflater.inflate(R.layout.food_item_layout, parent, false);

        String foodItem = getItem(position);

        TextView foodTextView = (TextView) foodView.findViewById(R.id.food_item_text_view);
        foodTextView.setText(foodItem);

        ImageView foodImageView = (ImageView) foodView.findViewById(R.id.food_image);
        //decide which picture goes with which food option
        foodImageView.setImageResource(restaurantHandler.getMenu().get(position).imageId);

        return foodView;
    }
}
