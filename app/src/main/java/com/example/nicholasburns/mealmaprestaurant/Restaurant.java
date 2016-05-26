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

    //constructor for a Restaurant object
    public Restaurant(String title, int imgID, List<FoodItem> fi, String primaryGenre, double lt, double lg){
        lat = lt;
        lng = lg;
        name = title;
        imageID = imgID;
        menuItems = fi;
        genre = primaryGenre;
    }

    //returns the name of the Restaurant
    public String getName(){
        return name;
    }


    //returns the ImageID of the Restaurant
    public int getImageID(){
        return imageID;
    }

    //returns the menu of the Restaurant
    public List<FoodItem> getMenuItems() {
        return menuItems;
    }

    //returns the genre of the Restaurant
    public String getGenre(){
        return genre;
    }

    //returns the latitude of the restaurant
    public double getLat(){
        return lat;
    }

    //returns the longitude of the restaurant
    public double getLng(){
        return lng;
    }
}
