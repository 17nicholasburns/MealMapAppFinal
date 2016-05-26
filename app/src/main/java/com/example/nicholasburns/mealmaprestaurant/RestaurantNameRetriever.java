package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**Retrieves a list of names of restaurants in a checked genre to be returned to RestaurantRetriever.java, which
 * will use them to create Restaurant objects
 * Created by 17nicholasburns on 4/13/2016.
 */
public class RestaurantNameRetriever {
    private static List<String> restaurantNames;

    //returns a List<String> of the names of restaurants in this genre
    public static List<String> getRestaurantNames(Context ctxt, String genreName) {
        restaurantNames = new ArrayList<String>();
        try {
            BufferedReader restaurantNameReader = new BufferedReader(new InputStreamReader(ctxt.getAssets().open("restaurantNames.txt")));
            String names = restaurantNameReader.readLine();
            names = getListOfRestaurantsInGenre(names, genreName);
            while(names.length()>0){
                String nextName = getNextName(names, ctxt);
                restaurantNames.add(nextName.substring(0,nextName.length()-1));
                names = names.substring(nextName.length());
            }

        }
        catch (IOException e){
            CharSequence text = "RestaurantNameRetriever encountered an error while reading\nrestaurantNames.txt";
            Toast restaurantNamesErrorToast = Toast.makeText(ctxt, text, Toast.LENGTH_LONG);
            restaurantNamesErrorToast.show();
        }
        return restaurantNames;
    }

    //returns the next name of the next restaurant in the genre
    private static String getNextName(String names, Context ctxt) {
        String nextName;
        int locOfEnd;

        if(names.contains(".")){
            locOfEnd = names.indexOf(".");
        }
        else{
            locOfEnd = names.indexOf("!");
        }

        nextName = names.substring(0, locOfEnd + 1);
        return nextName;
    }

    //returns a string of only the names of restaurants in this genre
    private static String getListOfRestaurantsInGenre(String wholeString, String genreName){
        String newString ="";
        int i;
        String genreBeginning = "GENRE:"+genreName+"(START)=";
        String startOfGenre = wholeString.substring(wholeString.indexOf("GENRE:"+genreName+"(START)="), wholeString.indexOf("GENRE:"+genreName+"(START)=")+genreBeginning.length());
        String currentLetter = "";
        i = wholeString.indexOf(genreBeginning) + startOfGenre.length();

        while(!currentLetter.equals("!")){
            currentLetter = wholeString.substring(i, i+1);
            newString = newString + currentLetter;
            i++;
        }

        return newString;
    }
}