package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**Navigates rating text files to find the rating of a FoodItem
 * Created by Taquito on 5/25/2016.
 */
public class RatingReader {
    private static Context ratingContext;

    public static int getRating(String foodItem, String genre, Context c){
        ratingContext = c;
        String ratingFileName = getRatingFileName(genre);
        int rating = 0;
        String foodLocText = "("+foodItem+"=";

        try{
            BufferedReader ratingReader = new BufferedReader(new InputStreamReader(ratingContext.getAssets().open(ratingFileName)));
            String ratings = ratingReader.readLine();

            int indOfFoodItem = ratings.indexOf(foodLocText);
            int indOfRatingString = indOfFoodItem+foodLocText.length();

            String ratingString = ratings.substring(indOfRatingString, indOfRatingString+1);

            rating = Integer.parseInt(ratingString);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(c, "Failed to read ratings text file", Toast.LENGTH_SHORT).show();
        }
        return rating;
    }

    private static String getRatingFileName(String genre) {
        if(genre.equalsIgnoreCase("Fast Food")) {
            return "FastFoodRatings.txt";
        }
        else if(genre.equalsIgnoreCase("Dine-In")) {
            return "DineInRatings.txt";
        }
        else if(genre.equalsIgnoreCase("Italian")) {
            return "ItalianRatings.txt";
        }
        else if(genre.equalsIgnoreCase("Mexican")) {
            return "MexicanRatings.txt";
        }
        else if(genre.equalsIgnoreCase("Chinese")) {
            return "ChineseRatings.txt";
        }
        else if(genre.equalsIgnoreCase("Sea Food")) {
            return "SeaFoodRatings.txt";
        }
        else {
            return "FastFoodRatings.txt";
        }
    }
}