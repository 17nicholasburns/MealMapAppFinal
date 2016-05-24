package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.widget.Toast;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**Reads the Genres.txt file to get the genres for the program
 * Created by 17nicholasburns on 3/15/2016.
 */
public class GenreRetriever {
    private Context genreCntxt;
    public List<String> genres = new ArrayList<String>();
    public GenreRetriever(Context ctxt){
        genreCntxt = ctxt;


    }
    //read the genres.txt file and add them to an arrayList of strings representing genres
    public List<String> getListOfGenres(){
        try {
            BufferedReader genreReader = new BufferedReader(new InputStreamReader(genreCntxt.getAssets().open("genres.txt")));
            String genre = genreReader.readLine();

            genre = genre.substring(7);
            while(genre.length()>0){
                String nextGenre = getNextGenre(genre);
                genres.add(nextGenre.substring(0, nextGenre.length()-1));
                genre = genre.substring(nextGenre.length());
            }

        }
        catch (IOException e) {
            CharSequence text = "GenreRetriever was unable to read genres.txt";
            Toast genresNotAvailableToast = Toast.makeText(genreCntxt, text, Toast.LENGTH_SHORT);
            genresNotAvailableToast.show();
        }
        return genres;
    }
    private String getNextGenre(String g){
        String nextGenre = "";
        int locOfEnd;
        if(g.contains(".")) {
            locOfEnd = g.indexOf(".");
        }
        else{
            locOfEnd = g.indexOf("!");
        }
        nextGenre = g.substring(0, locOfEnd + 1);
        return nextGenre;
    }
}
