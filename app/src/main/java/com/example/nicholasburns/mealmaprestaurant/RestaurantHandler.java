package com.example.nicholasburns.mealmaprestaurant;

import java.util.List;

/**Remembers and handles actions to the restaurant currently in use
 * used for references and such that can not be transferred through putextra method
 * Created by 17nicholasburns on 3/7/2016.
 */
public class RestaurantHandler {
    private String name;
    private int imgId;
    private List<FoodItem> menu;
    private String genre;

    //constructor for a RestaurantHandler
    public RestaurantHandler(Restaurant restaurant){
        name = restaurant.getName();
        imgId = restaurant.getImageID();
        menu = restaurant.getMenuItems();
        genre = restaurant.getGenre();
    }

    //returns the name of the restaurant it is handling
    public String getName(){
        return name;
    }

    //returns the imageId of the restaurant it is handling
    public int getImgId(){
        return imgId;
    }

    //returns the menu of the restaurant it is handling
    public List<FoodItem> getMenu(){
        return menu;
    }

    //returns the genre of the restaurant it is handling
    public String getGenre(){
        return genre;
    }
}
