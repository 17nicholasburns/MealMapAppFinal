package com.example.nicholasburns.mealmaprestaurant;

/** determines name and value of listview item in genre filter list
 * Created by 17nicholasburns on 4/28/2016.
 */
public class GenreListViewModel {
    private String name;
    private int value;

    public GenreListViewModel(String n, int v){
        name = n;
        value = v;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return value;
    }
}
