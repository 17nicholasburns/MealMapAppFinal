package com.example.nicholasburns.mealmaprestaurant;

import java.util.ArrayList;

/**Checks whether value of the rating has been changed
 * Created by Taquito on 5/25/2016.
 */
public class RatingChangeChecker {
    private ArrayList<String> ChangedFoodItems;
    private int[] numsOfAnswers;
    private int[] ratings;
    //constructor for a RatingChangeChecker
    public RatingChangeChecker(){
        ChangedFoodItems = new ArrayList<>();
        numsOfAnswers = new int[15];
        ratings = new int[15];
    }

    //makes sure that the changes have been added
    public void addNewChange(String foodName, int rating, int numOfAnswers){
        ChangedFoodItems.add(foodName);
        for(int i = 0; i < numsOfAnswers.length; i++){
            if(numsOfAnswers[i] == 0){
                numsOfAnswers[i] = numOfAnswers;
                i = numsOfAnswers.length;
            }
        }

        for(int i = 0; i < ratings.length; i++){
            if(ratings[i] == 0){
                ratings[i] = rating;
                i = ratings.length;
            }
        }
    }

    //confirms whether or not this foodItem should use its original rating
    public boolean checkIfChanged(String foodName){
        boolean found = false;
        for(int i = 0; i < ChangedFoodItems.size(); i++){
            if(ChangedFoodItems.get(i).equalsIgnoreCase(foodName)){
                found = true;
            }
        }
        return found;
    }

    //returns what the new rating should be
    public int getRating(String foodItem){
        int rate = 0;
        for(int i = 0; i < ChangedFoodItems.size(); i++){
            if(ChangedFoodItems.get(i).equalsIgnoreCase(foodItem)){
                rate = ratings[i];
            }
        }
        return rate;
    }

    //returns what number of responses the foodItem should have
    public int getNumOfAnswers(String foodItem){
        int numOfAnswers = 0;
        for(int i = 0; i < ChangedFoodItems.size(); i++){
            if(ChangedFoodItems.get(i).equalsIgnoreCase(foodItem)){
                numOfAnswers = ratings[i];
            }
        }
        return numOfAnswers;
    }
}
