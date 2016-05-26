package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**Location Retriever created specifically for the FoodItemSearch class
 * Created by Taquito on 5/25/2016.
 */
public class FoodLocationRetriever {
    private static List<String> restaurantLocations = new ArrayList<String>();
    private static List<String> restaurantNames = new ArrayList<String>();

    //Retrieves the locations for food items found in the FoodItemSearch
    public static List<String> getFoodLocations(Context ctxt, String genreName, List<String> rNames){
        restaurantNames = rNames;
        restaurantLocations.clear();
        try{
            BufferedReader restaurantLocationReader = new BufferedReader(new InputStreamReader(ctxt.getAssets().open("restaurantLocations.txt")));
            String locations = restaurantLocationReader.readLine();
            int nameNum = 0;
            locations = getGenreString(locations, genreName, ctxt);

            while(locations.length()>0 && nameNum < restaurantNames.size()){
                String nextLocation = getNextLocation(nameNum, locations, ctxt);
                nextLocation = getLocationOnly(nextLocation);
                restaurantLocations.add(nextLocation);
                String getToNextRest ="**";
                locations = locations.substring(locations.indexOf(getToNextRest)+getToNextRest.length());
                nameNum++;
            }
        }
        catch (IOException e){
            CharSequence text = "RestaurantLocationRetriever encountered an error while reading\nrestaurantLocations.txt";
            Toast restaurantLocationsErrorToast = Toast.makeText(ctxt, text, Toast.LENGTH_LONG);
            restaurantLocationsErrorToast.show();
        }
        return restaurantLocations;
    }

    //gets the string of the beginning and end of the genre
    private static String getGenreString(String wholeString, String genreName, Context ctxt) {
        String newString = "";

        String StartOfGenre = "!!"+genreName+"!!=";
        int locOfStartOfGenre = wholeString.indexOf(StartOfGenre);

        String EndOfGenre = "=??"+genreName+"??";
        int locOfEndOfGenre = wholeString.indexOf(EndOfGenre);

        newString = wholeString.substring(locOfStartOfGenre, locOfEndOfGenre + EndOfGenre.length());

        return newString;
    }

    //gets the next location of a food item by searching for its restaurant
    private static String getNextLocation(int nameNum, String genreString, Context ctxt) {
        String newString ="";
        int i = 0;

        int startOfRestaurant = genreString.indexOf(restaurantNames.get(nameNum));
        genreString = genreString.substring(startOfRestaurant + restaurantNames.get(nameNum).length());
        newString = genreString.substring(genreString.indexOf("("),genreString.indexOf(")")+1);

        return newString;
    }

    //returns a string that only has the numerical location of the restaurant
    private static String getLocationOnly(String nextLocation){
        int start = nextLocation.indexOf("(");
        int end = nextLocation.indexOf(")");
        return nextLocation.substring(start+1, end);
    }
}
