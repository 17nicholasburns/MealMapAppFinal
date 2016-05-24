package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.ads.formats.NativeAd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 17nicholasburns on 4/13/2016.
 */
public class RestaurantImageRetriever {
    private static List<String> restaurantNames;

    public static int[]  getRestaurantImages(Context ctxt, String genreName, List<String> rNames){
        restaurantNames = rNames;
        int [] restaurantImages = new int[rNames.size()];

        String referenceFileName = getReferenceFileName(genreName);

        try{
            BufferedReader restaurantImageReader = new BufferedReader(new InputStreamReader(ctxt.getAssets().open(referenceFileName)));
            String images = restaurantImageReader.readLine();

            while(images.length()>0){
                int nextImageId = getNextImageId(ctxt, images);
            }
        }
        catch (IOException e){
            Toast.makeText(ctxt, "Error retrieving restaurant images", Toast.LENGTH_LONG).show();
        }
        return restaurantImages;
    }

    private static int getNextImageId(Context ctxt, String imageIds) throws IOException {

        return 0;
    }

    private static String getReferenceFileName(String gName){
        String rString;

        switch (gName){
            case "Fast Food":
                rString = "FastFoodImages.txt";
                break;
            case "Dine-In":
                rString = "DineInImages.txt";
                break;
            case "Italian":
                rString = "ItalianImages.txt";
                break;
            case "Mexican":
                rString = "MexicanImages.txt";
                break;
            case "Sea Food":
                rString = "SeaFoodImages.txt";
                break;
            default:
                rString = "FastFoodImages.txt";
                break;
        }
        return rString;
    }
}
