package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**updates the text file that holds the ratings of food items
 * Created by Taquito on 5/25/2016.
 */
public class RatingUpdater {
    private static Context ratingContext;

    //changes the rating of the newly rated item
    public static int changeRating(String foodItem, String genre, int nextRating, Context c){
        ratingContext = c;
        String ratingFileName = getRatingFileName(genre);
        String ratingPathName = getRatingFilePath(genre);
        int rating = 0;
        int numOfAnswers = 0;
        String foodLocText = "("+foodItem+"=";

        try{
            BufferedReader ratingReader = new BufferedReader(new InputStreamReader(ratingContext.getAssets().open(ratingFileName)));

            String ratings = ratingReader.readLine();

            int indOfFoodItem = ratings.indexOf(foodLocText);
            int indOfRatingString = indOfFoodItem+foodLocText.length();

            String ratingString = ratings.substring(indOfRatingString, indOfRatingString+1);
            String numOfAnswersSTring = ratings.substring(indOfRatingString+2, indOfRatingString+3);

            rating = Integer.parseInt(ratingString);
            numOfAnswers = Integer.parseInt(numOfAnswersSTring);

            int newRating = rating*numOfAnswers;
            newRating = newRating + nextRating;

            numOfAnswers++;

            rating = newRating/numOfAnswers;

            String firstHalf = ratings.substring(0, indOfFoodItem);
            String secondHalf = ratings.substring(indOfFoodItem);
            secondHalf = secondHalf.substring(secondHalf.indexOf(")")+1);

            ratings = firstHalf +foodLocText+ rating + numOfAnswers +")" + secondHalf;

            BufferedWriter ratingWriter = new BufferedWriter(new FileWriter(ratingPathName));

            ratingWriter.write(ratings);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return nextRating;
    }

    //returns the file name that FoodItem's rating is in
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

    //returns the file path that FoodItem's rating should be written to
    private static String getRatingFilePath(String genre) {
        if(genre.equalsIgnoreCase("Fast Food")) {
            return "C:\\Users\\Taquito\\AndroidStudioProjects\\MealMapRestaurant2\\app\\src\\main\\assets\\FastFoodRatings.txt";
        }
        else if(genre.equalsIgnoreCase("Dine-In")) {
            return "C:\\Users\\Taquito\\AndroidStudioProjects\\MealMapRestaurant2\\app\\src\\main\\assets\\DineInRatings.txt";
        }
        else if(genre.equalsIgnoreCase("Italian")) {
            return "C:\\Users\\Taquito\\AndroidStudioProjects\\MealMapRestaurant2\\app\\src\\main\\assets\\ItalianRatings.txt";
        }
        else if(genre.equalsIgnoreCase("Mexican")) {
            return "C:\\Users\\Taquito\\AndroidStudioProjects\\MealMapRestaurant2\\app\\src\\main\\assets\\MexicanRatings.txt";
        }
        else if(genre.equalsIgnoreCase("Chinese")) {
            return "C:\\Users\\Taquito\\AndroidStudioProjects\\MealMapRestaurant2\\app\\src\\main\\assets\\ChineseRatings.txt";
        }
        else if(genre.equalsIgnoreCase("Sea Food")) {
            return "C:\\Users\\Taquito\\AndroidStudioProjects\\MealMapRestaurant2\\app\\src\\main\\assets\\SeaFoodRatings.txt";
        }
        else {
            return "C:\\Users\\Taquito\\AndroidStudioProjects\\MealMapRestaurant2\\app\\src\\main\\assets\\FastFoodRatings.txt";
        }
    }
}
