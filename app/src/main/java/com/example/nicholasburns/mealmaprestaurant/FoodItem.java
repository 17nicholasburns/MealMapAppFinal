package com.example.nicholasburns.mealmaprestaurant;

/** Food item object
 * Will be put into a list of food items for each restaurant
 * Created by 17nicholasburns on 3/3/2016.
 */
public class FoodItem {
    public String name;
    public int imageId;
    public String description;
    public FoodItem(String n, int imgId, String des){
        name = n;
        imageId = imgId;
        description = des;
    }
}
