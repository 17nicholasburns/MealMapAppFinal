package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**Rating adapter creates the layout for the rating you select
 * Created by Taquito on 5/25/2016.
 */
public class RatingAdapter extends ArrayAdapter<String>{
    //creates a rating adapter object
    public RatingAdapter(Context context, String [] values){
        super(context, R.layout.rating_item_layout, values);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater ratingInflater = LayoutInflater.from(getContext());

        View ratingView = ratingInflater.inflate(R.layout.rating_item_layout, parent, false);

        ImageView s1 = (ImageView) ratingView.findViewById(R.id.rating_image1);
        ImageView s2 = (ImageView) ratingView.findViewById(R.id.rating_image2);
        ImageView s3 = (ImageView) ratingView.findViewById(R.id.rating_image3);
        ImageView s4 = (ImageView) ratingView.findViewById(R.id.rating_image4);
        ImageView s5 = (ImageView) ratingView.findViewById(R.id.rating_image5);

        if(position==0){
            s1.setImageResource(R.drawable.fullstar);
            s2.setImageResource(R.drawable.emptystar);
            s3.setImageResource(R.drawable.emptystar);
            s4.setImageResource(R.drawable.emptystar);
            s5.setImageResource(R.drawable.emptystar);
        }
        else if(position==1){
            s1.setImageResource(R.drawable.fullstar);
            s2.setImageResource(R.drawable.fullstar);
            s3.setImageResource(R.drawable.emptystar);
            s4.setImageResource(R.drawable.emptystar);
            s5.setImageResource(R.drawable.emptystar);
        }
        else if(position==2){
            s1.setImageResource(R.drawable.fullstar);
            s2.setImageResource(R.drawable.fullstar);
            s3.setImageResource(R.drawable.fullstar);
            s4.setImageResource(R.drawable.emptystar);
            s5.setImageResource(R.drawable.emptystar);
        }
        else if(position==3){
            s1.setImageResource(R.drawable.fullstar);
            s2.setImageResource(R.drawable.fullstar);
            s3.setImageResource(R.drawable.fullstar);
            s4.setImageResource(R.drawable.fullstar);
            s5.setImageResource(R.drawable.emptystar);
        }
        else if(position==4){
            s1.setImageResource(R.drawable.fullstar);
            s2.setImageResource(R.drawable.fullstar);
            s3.setImageResource(R.drawable.fullstar);
            s4.setImageResource(R.drawable.fullstar);
            s5.setImageResource(R.drawable.fullstar);
        }
        else{
            s1.setImageResource(R.drawable.emptystar);
            s2.setImageResource(R.drawable.emptystar);
            s3.setImageResource(R.drawable.emptystar);
            s4.setImageResource(R.drawable.emptystar);
            s5.setImageResource(R.drawable.emptystar);
        }
        return ratingView;
    }
}
