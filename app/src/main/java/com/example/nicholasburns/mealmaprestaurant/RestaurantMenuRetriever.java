package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**retrieves the menu for a restaurant
 * Created by 17nicholasburns on 5/20/2016.
 */
public class RestaurantMenuRetriever {
    public static List<String> restaurantMenu;
    public static String genreName;
    public static String restaurantName;

    //Returns a list of strings representing the Restaurant's menu
    public static List<String> getRestaurantMenu(String gn, String rn, Context ctxt){
        genreName = gn;
        restaurantName = rn;
        restaurantMenu = new ArrayList<>();
        String genreMenusFileName = findGenreMenusTxt(genreName);
        try{
            BufferedReader restaurantMenuReader = new BufferedReader(new InputStreamReader(ctxt.getAssets().open(genreMenusFileName)));
            String genreMenus = restaurantMenuReader.readLine();

            String menuString = getMenuString(genreMenus);

            while(menuString.length()>0) {
                String newItem;
                newItem = getNextItem(menuString);
                restaurantMenu.add(newItem);
                menuString = menuString.substring(menuString.indexOf(".")+1);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ctxt, "Error reading menu text", Toast.LENGTH_LONG).show();
        }
        return restaurantMenu;
    }

    //retrieves the next item on the menu
    private static String getNextItem(String menuString) {
        String newString = "";
        newString = menuString.substring(0, menuString.indexOf("."));
        return newString;
    }

    //retrieves the string of the menu of only the restaurant that is being processed
    private static String getMenuString(String genreMenus) {
        String newString = "";

        String startOfMenu = "!!"+restaurantName+"!!(START)=";
        String endOfMenu ="!!"+restaurantName+"!!(END)";

        int locOfStartOfMenu = genreMenus.indexOf(startOfMenu);
        int locOfEndOfMenu = genreMenus.indexOf(endOfMenu);

        newString = genreMenus.substring(locOfStartOfMenu +startOfMenu.length(), locOfEndOfMenu);

        return newString;
    }

    //returns a string of the name of the .txt file containing this restaurant's menu
    public static String findGenreMenusTxt(String genreName) {
        if(genreName.equalsIgnoreCase("Fast Food")) {
            return "FastFoodMenus.txt";
        }
        else if(genreName.equalsIgnoreCase("Dine-In")) {
            return "DineInMenus.txt";
        }
        else if(genreName.equalsIgnoreCase("Italian")) {
            return "ItalianMenus.txt";
        }
        else if(genreName.equalsIgnoreCase("Mexican")) {
            return "MexicanMenus.txt";
        }
        else if(genreName.equalsIgnoreCase("Chinese")) {
            return "ChineseMenus.txt";
        }
        else if(genreName.equalsIgnoreCase("Sea Food")) {
            return "SeaFoodMenus.txt";
        }
        else {
            return null;
        }
    }
}
