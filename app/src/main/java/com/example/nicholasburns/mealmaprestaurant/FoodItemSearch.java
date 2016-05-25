package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**uses the search bar to search for food items
 * Created by 17nicholasburns on 5/23/2016.
 */
public class FoodItemSearch {
    private static String foodName;
    public static List<FoodItem> foodItems;
    public static List<Restaurant> restaurants = new ArrayList<Restaurant>();
    private static Context ctxt;
    public static List<Restaurant> searchForFood(String searchText, Context c, List<String> genres){
        ctxt = c;
        restaurants.clear();
        for(int i = 0; i < genres.size(); i++){
            String menuFileName = RestaurantMenuRetriever.findGenreMenusTxt(genres.get(i));
            Toast.makeText(ctxt, "Reading Genre Menus of" + genres.get(i), Toast.LENGTH_SHORT).show();
            try {
                BufferedReader restaurantMenuReader = new BufferedReader(new InputStreamReader(ctxt.getAssets().open(menuFileName)));
                String genreMenus = restaurantMenuReader.readLine();
                String restaurantMenus = genreMenus.substring(0,genreMenus.length());


                while(genreMenus.length()>0) {
                    int locOfFoundText = genreMenus.indexOf(searchText);
                    if (locOfFoundText>0){

                        foodName = findFullItemName(locOfFoundText, genreMenus, ctxt);

                        Toast.makeText(ctxt, foodName, Toast.LENGTH_SHORT).show();

                        Restaurant restOFFood = findFoodRestaurant(foodName, restaurantMenus, genres.get(i));
                        restaurants.add(restOFFood);

                        Toast.makeText(ctxt, restOFFood.getName(), Toast.LENGTH_SHORT).show();

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
        for(int i = 0; i < restaurants.size(); i++){
            Toast.makeText(ctxt, "restaurants list #"+i+": "+restaurants.get(i).getName(), Toast.LENGTH_SHORT).show();
            Toast.makeText(ctxt, "restaurants list #"+i+" location: "+restaurants.get(i).getLat() +" "+ restaurants.get(i).getLng(), Toast.LENGTH_SHORT).show();
        }
        return restaurants;
    }

    private static Restaurant findFoodRestaurant(String foodName, String genreMenus, String genre) {
        int indOfStart = genreMenus.indexOf(foodName);
        int indOfStartOfRest = 0;
        for(int i = indOfStart; i > 0 && !genreMenus.substring(i,i+1).equals("("); i--){
            indOfStartOfRest = i;
        }

        indOfStartOfRest = indOfStartOfRest-4;
        for(int i = indOfStartOfRest; i > 0 && !genreMenus.substring(i,i+1).equals("!"); i--){
            indOfStartOfRest = i;
        }

        String tempMenu = genreMenus.substring(indOfStartOfRest);
        tempMenu = tempMenu.substring(0, tempMenu.indexOf("!"));


        List<String> tempList = new ArrayList<>();

        tempList.add(tempMenu);
        for(int i = 0; i < tempList.size(); i++){
            Toast.makeText(ctxt, "tempList item "+i+" "+tempList.get(i), Toast.LENGTH_SHORT).show();
        }

        List<String> locs = FoodLocationRetriever.getFoodLocations(ctxt, genre, tempList);
        LatLng coords = getLatLng(locs);

        List<String> RestaurantMenu = RestaurantMenuRetriever.getRestaurantMenu(genre, tempMenu, ctxt);

        List<FoodItem> foodItemMenu = RestaurantRetriever.getMenuFoodItems(RestaurantMenu, ctxt);

        Restaurant retRest = new Restaurant(tempMenu, R.drawable.restrant, foodItemMenu, genre, coords.latitude, coords.longitude);
        Toast.makeText(ctxt,"findFoodRestaurant return:" +retRest.getName(), Toast.LENGTH_SHORT).show();
        return retRest;
    }

    private static LatLng getLatLng(List<String> locs) {
        String latString = locs.get(0).substring(0, locs.get(0).indexOf(","));
        String lngString = locs.get(0).substring(locs.get(0).indexOf(",") + 1, locs.get(0).length());

        double lat = Double.parseDouble(latString);
        double lng = Double.parseDouble(lngString);

        return new LatLng(lat, lng);
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
        return genreMenu.substring(indexOfStart, indexOfEnd+1);
    }
}
