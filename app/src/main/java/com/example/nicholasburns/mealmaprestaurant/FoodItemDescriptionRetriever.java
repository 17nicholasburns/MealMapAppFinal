package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/** retrieves the description of each FoodIem
 * Created by 17nicholasburns on 5/26/2016.
 */
public class FoodItemDescriptionRetriever {

    //retrieves the descriptions of the food
    public static String getFoodItemDescription(String foodItem, String genre, Context c){
        String descriptionFileName = getDescriptionFileName(genre);
        String foodLocText = "("+foodItem+"=";

        String descriptionString = "";

        try {
            BufferedReader descriptionReader = new BufferedReader(new InputStreamReader(c.getAssets().open(descriptionFileName)));
            String descriptions = descriptionReader.readLine();

            int indOfFoodItem = descriptions.indexOf(foodLocText);
            int indOfDescriptionString = indOfFoodItem+foodLocText.length();
            descriptions = descriptions.substring(indOfDescriptionString);

            descriptionString = descriptions.substring(0, descriptions.indexOf(")"));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(c, "Error reading description file", Toast.LENGTH_SHORT).show();
        }
        return descriptionString;
    }

    //gets the file name of the descriptions based on their genre
    private static String getDescriptionFileName(String genre) {
        if(genre.equalsIgnoreCase("Fast Food")) {
            return "FastFoodDescriptions.txt";
        }
        else if(genre.equalsIgnoreCase("Dine-In")) {
            return "DineInDescriptions.txt";
        }
        else if(genre.equalsIgnoreCase("Italian")) {
            return "ItalianDescriptions.txt";
        }
        else if(genre.equalsIgnoreCase("Mexican")) {
            return "MexicanDescriptions.txt";
        }
        else if(genre.equalsIgnoreCase("Chinese")) {
            return "ChineseDescriptions.txt";
        }
        else if(genre.equalsIgnoreCase("Sea Food")) {
            return "SeaFoodDescriptions.txt";
        }
        else {
            return "FastFoodDescriptions.txt";
        }
    }
}
