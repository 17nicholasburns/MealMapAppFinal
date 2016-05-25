package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**Gets a list of restaurants that pass through the filter
 * Created by 17nicholasburns on 4/13/2016.
 */
public class RestaurantRetriever {
    private static List<Restaurant> restaurants = new ArrayList<Restaurant>();
    private static List<String> restaurantNames;
    private static List<String> restaurantLocations = new ArrayList<String>();
    private static double[] lats;
    private static double[] lngs;
    private static String genreName;

    public static List<Restaurant> getListOfRestaurantsByGenre(Context restCtxt, String gn){
        genreName = gn;
        restaurantNames = new ArrayList<String>();
        restaurantNames = RestaurantNameRetriever.getRestaurantNames(restCtxt, gn);


        restaurantLocations.clear();
        restaurantLocations = RestaurantLocationRetriever.getRestaurantLocations(restCtxt, gn, restaurantNames);
        lats = new double[restaurantLocations.size()];
        lngs = new double[restaurantLocations.size()];


        getLatAndLng(restaurantLocations, restCtxt);

        for (int i= 0; i < restaurantNames.size(); i++){
            List<String> menuStrings = RestaurantMenuRetriever.getRestaurantMenu("Fast Food", "McDonald's", restCtxt);
            List<FoodItem> menu = getMenuFoodItems(menuStrings, restCtxt);
            makeRestaurant(restaurantNames.get(i), lats[i], lngs[i], R.drawable.restrant, menu);
        }
        return restaurants;
    }

    private static void makeRestaurant(String name, double lat, double lng, int imageID, List<FoodItem> menu){
        restaurants.add(new Restaurant(name, imageID, menu, genreName, lat, lng));
    }

    private static void getLatAndLng(List<String> latsAndLngs, Context ctxt){
        for(int i = 0; i < latsAndLngs.size(); i++){
            String latString = latsAndLngs.get(i).substring(0, latsAndLngs.get(i).indexOf(","));
            String lngString = latsAndLngs.get(i).substring(latsAndLngs.get(i).indexOf(",") + 1, latsAndLngs.get(i).length());

            double lat = Double.parseDouble(latString);
            double lng = Double.parseDouble(lngString);

            lats[i] = lat;
            lngs[i] = lng;
        }
    }
    public static List<FoodItem> getMenuFoodItems(List<String> menuStrings, Context restCtxt){
        List<FoodItem> menu = new ArrayList<>();
        for(int i = 0; i < menu.size(); i++) {
            menu.add(new FoodItem(menuStrings.get(i), R.drawable.food1, restCtxt.getResources().getString(R.string.food1_description)));
        }
        return menu;
    }
}
