package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**uses the search bar to search for food items
 * Created by 17nicholasburns on 5/23/2016.
 */
public class FoodItemSearch {
    private static String foodName;
    public static List<FoodItem> foodItems;
    public static List<LatLng> foodLocations;
    public static void searchForFood(String searchText, Context ctxt, List<String> genres){
        for(int i = 0; i < genres.size(); i++){
            String menuFileName = RestaurantMenuRetriever.findGenreMenusTxt(genres.get(i));

            try {
                BufferedReader restaurantMenuReader = new BufferedReader(new InputStreamReader(ctxt.getAssets().open(menuFileName)));
                String genreMenus = restaurantMenuReader.readLine();
                while(genreMenus.length()>0) {
                    int locOfFoundText = genreMenus.indexOf(searchText);
                    if (locOfFoundText>=0){
                        foodName = findFullItemName(locOfFoundText, genreMenus, ctxt);
                        Toast.makeText(ctxt, foodName, Toast.LENGTH_LONG).show();
                        Restaurant restOFFood = findFoodRestaurant(foodName, genreMenus);
                        genreMenus = genreMenus.substring(genreMenus.indexOf(foodName) +foodName.length());
                    }
                    else{
                        genreMenus = "";
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(ctxt, "Error reading menus while searching for food items", Toast.LENGTH_LONG).show();
            }
        }
    }

    private static Restaurant findFoodRestaurant(String foodName, String genreMenus) {

        return null;
    }

    private static String findFullItemName(int locOfFoundText, String genreMenu, Context ctxt) {
        int indexOfStart = 0;
        int indexOfEnd = 0;
        for(int i = locOfFoundText; i > 0 && !(genreMenu.substring(i, i+1).equalsIgnoreCase(".")||genreMenu.substring(i, i+1).equalsIgnoreCase("=")); i--){
            indexOfStart = i;
        }
        for(int i = locOfFoundText; i < genreMenu.length() && !genreMenu.substring(i, i+1).equalsIgnoreCase("."); i++){
            indexOfEnd = i;
        }
        Toast.makeText(ctxt, genreMenu.substring(indexOfStart, indexOfEnd+1),Toast.LENGTH_SHORT).show();
        return genreMenu.substring(indexOfStart, indexOfEnd+1);
    }
}
