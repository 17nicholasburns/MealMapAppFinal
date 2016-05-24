package com.example.nicholasburns.mealmaprestaurant;

import java.util.ArrayList;
import java.util.List;

/**Creates a Restaurant object to be used by the map
 * Created by 17nicholasburns on 3/3/2016.
 */
public class Restaurant {
    private String name;
    private int imageID;
    private List<FoodItem> menuItems;
    private String genre;
    private double lat;
    private double lng;
    public Restaurant(String title, int imgID, List<FoodItem> fi, String primaryGenre, double lt, double lg){
        lat = lt;
        lng = lg;
        name = title;
        imageID = imgID;
        menuItems = fi;
        genre = primaryGenre;
    }

    public String getName(){
        return name;
    }


    public int getImageID(){
        return imageID;
    }

    public List<FoodItem> getMenuItems() {
        return menuItems;
    }

    public String getGenre(){
        return genre;
    }

    public double getLat(){
        return lat;
    }

    public double getLng(){
        return lng;
    }
}
