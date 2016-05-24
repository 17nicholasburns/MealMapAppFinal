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
    public RestaurantHandler(Restaurant restaurant){
        name = restaurant.getName();
        imgId = restaurant.getImageID();
        menu = restaurant.getMenuItems();
        genre = restaurant.getGenre();
    }

    public String getName(){
        return name;
    }

    public int getImgId(){
        return imgId;
    }

    public List<FoodItem> getMenu(){
        return menu;
    }

    public String getGenre(){
        return genre;
    }
}
