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
    public static List<String> restaurantMenu = new ArrayList<String>();
    public static String genreName;
    public static String restaurantName;

    public static List<String> getRestaurantMenu(String gn, String rn, Context ctxt){
        genreName = gn;
        restaurantName = rn;
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
            return restaurantMenu;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ctxt, "Error reading menu text", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    private static String getNextItem(String menuString) {
        String newString = "";
        newString = menuString.substring(0, menuString.indexOf("."));
        return newString;
    }

    private static String getMenuString(String genreMenus) {
        String newString = "";
        String startOfMenu = "!!"+restaurantName+"!!(START)=";
        String endOfMenu ="!!"+restaurantName+"!!(END)";
        int locOfStartOfMenu = genreMenus.indexOf(startOfMenu);
        int locOfEndOfMenu = genreMenus.indexOf(endOfMenu);
        newString = genreMenus.substring(locOfStartOfMenu +startOfMenu.length(), locOfEndOfMenu);
        return newString;
    }

    public static String findGenreMenusTxt(String genreName) {
        if(genreName.equalsIgnoreCase("Fast Food")) {
            return "FastFoodMenus.txt";
        }
        else if(genreName.equalsIgnoreCase("Fast Food")) {
            return "DineInMenus.txt";
        }
        else if(genreName.equalsIgnoreCase("Fast Food")) {
            return "ItalianMenus.txt";
        }
        else if(genreName.equalsIgnoreCase("Fast Food")) {
            return "MexicanMenus.txt";
        }
        else if(genreName.equalsIgnoreCase("Fast Food")) {
            return "ChineseMenus.txt";
        }
        else if(genreName.equalsIgnoreCase("Fast Food")) {
            return "SeaFoodMenus.txt";
        }
        else {
            return null;
        }
    }
}
