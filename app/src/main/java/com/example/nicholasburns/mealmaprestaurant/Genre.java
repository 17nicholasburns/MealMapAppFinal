package com.example.nicholasburns.mealmaprestaurant;

import java.util.List;

/**Genre object holds a list of restaurants in a genre of food
 * Created by 17nicholasburns on 3/10/2016.
 */
public class Genre {
    private List<Restaurant> restaurantsInGenre;
    private String name;

    public Genre(List<Restaurant> rIG, String n){
        name = n;
        restaurantsInGenre = rIG;
    }

    public String getName(){
        return name;
    }

}
