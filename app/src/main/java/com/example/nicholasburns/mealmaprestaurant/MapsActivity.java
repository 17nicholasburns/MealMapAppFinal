package com.example.nicholasburns.mealmaprestaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private Button searchButton;
    private EditText searchBar;
    private GoogleMap mMap;
    private List<FoodItem> showableFoodItems;
    private static RestaurantHandler restaurantHandler;
    private GenreRetriever gRetr = new GenreRetriever(this);
    private List<String> listOfGenres = new ArrayList<String>();
    private List<Restaurant> listOfRestaurants = new ArrayList<Restaurant>();
    private String [] returnGenres = new String[0];
    @Override//creates the map
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        searchButton= (Button)findViewById(R.id.food_item_search_button);
        searchBar = (EditText)findViewById(R.id.food_item_search_bar);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setPadding(0, 77, 0, 0);

        //call the genre retriever so the list of genre filters will be ready for the user
        GenreRetriever genRet = new GenreRetriever(this);
        listOfGenres = genRet.getListOfGenres();

        listOfRestaurants = RestaurantRetriever.getListOfRestaurantsByGenre(this, "Fast Food");
        //RestaurantMenuRetriever.getRestaurantMenu("Fast Food", "McDonald's", this);

        //List<String> tempList = new ArrayList<>();
        //tempList.add("Fast Food");
        //FoodItemSearch.searchForFood("burger",this, tempList);


        //testing the handler
        List<FoodItem> menu = new ArrayList<FoodItem>();
        menu.add(new FoodItem("Burger", R.drawable.food1, getResources().getString(R.string.food1_description)));
        menu.add(new FoodItem("Salad", R.drawable.food2, getResources().getString(R.string.food2_description)));
        menu.add(new FoodItem("Turkey", R.drawable.food3, getResources().getString(R.string.food3_description)));
        menu.add(new FoodItem("T-bone Steak", R.drawable.food4, getResources().getString(R.string.food4_description)));
        menu.add(new FoodItem("Horse?", R.drawable.food5, getResources().getString(R.string.food5_description)));
        Restaurant restaurant = new Restaurant("Pratyay", R.drawable.restrant, menu, "Food. Haha.", -34, 151);

        restaurantHandler = new RestaurantHandler(restaurant);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            //decides what happens when the marker's info window is clicked
            public void onInfoWindowClick(Marker marker) {
                Restaurant clickedRest = findClickedRestaurant(marker);
                if(clickedRest != null){
                    restaurantHandler = new RestaurantHandler(clickedRest);
                    clickedRestaurant(marker);
                }
            }
        });
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            //creates the restaurant's infoWindow
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                Restaurant clickedRest = findClickedRestaurant(marker);
                restaurantHandler = new RestaurantHandler(clickedRest);
                View infoWindow = getLayoutInflater().inflate(R.layout.restaurant_info_window_layout, null);

                ImageView restPic = (ImageView) infoWindow.findViewById(R.id.restaurant_info_window_image);
                restPic.setImageResource(restaurantHandler.getImgId());

                TextView restTitle = (TextView) infoWindow.findViewById(R.id.restaurant_info_window_title);
                restTitle.setText(restaurantHandler.getName());

                TextView restGenre = (TextView) infoWindow.findViewById((R.id.restaurant_info_window_genre));
                restGenre.setText(getResources().getString(R.string.info_window_genre_intro) + restaurantHandler.getGenre());
                return infoWindow;
            }
        });

        addNewRestaurantMarkers();
    }
    //swithches to the RestaurantActivity
    private void clickedRestaurant(Marker marker){
        Intent restIntent = new Intent(this, RestaurantActivity.class);

        final int RESULT = 1;

        startActivityForResult(restIntent, RESULT);

    }

    //accessable Restaurant handler is sent so methods remain consistent
    public static RestaurantHandler getRestaurantHandler(){
        return restaurantHandler;
    }

    //opens the sidebar and sends a list of genres
    public void sideBarButtonClick(View view) {
        Intent sidebarIntent = new Intent(this, GenresActivity.class);
        String [] genres;
        final int RESULT = 1;
        genres = new String[listOfGenres.size()];
        for (int i = 0;i < listOfGenres.size();i++) {
            genres[i] = listOfGenres.get(i);
        }

        sidebarIntent.putExtra("genreList", genres);

        startActivityForResult(sidebarIntent, RESULT);
    }

    public void foodItemSearchBarSearchOnClick(View view){
        mMap.clear();

        if(returnGenres.length <= 0){
            resetReturnGenres();
        }

        List<String> listOfReturnGenres = new ArrayList<>();
        for(int i = 0; i <returnGenres.length; i++){
            listOfReturnGenres.add(returnGenres[i]);
            Toast.makeText(this, listOfReturnGenres.get(i), Toast.LENGTH_SHORT).show();
        }

        String searchText = String.valueOf(searchBar.getText());
        List<Restaurant> searchedRestaurants = new ArrayList<>();
        if(searchText.length()>0){
            searchedRestaurants =  FoodItemSearch.searchForFood(searchText, this, listOfReturnGenres);
        }
        if(searchedRestaurants.size()>0){
            listOfRestaurants = searchedRestaurants;
            addNewRestaurantMarkers();
        }
    }

    private void resetReturnGenres() {
        List <String> tempReturnGenres = gRetr.getListOfGenres();
        returnGenres = new String[tempReturnGenres.size()];

        for(int i = 0; i < tempReturnGenres.size(); i++){
            returnGenres[i] = tempReturnGenres.get(i);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                returnGenres = data.getStringArrayExtra("listOfCheckedGenres");

                String genreToast = "";

                listOfRestaurants.clear();


                for(int i = 0; i < returnGenres.length; i++){
                    genreToast = genreToast + returnGenres[i];
                }
                for(int i = 0; i < returnGenres.length; i++){
                    listOfRestaurants.addAll(RestaurantRetriever.getListOfRestaurantsByGenre(this, returnGenres[i]));
                }
                addNewRestaurantMarkers();
                Toast.makeText(this, genreToast, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Restaurant findClickedRestaurant(Marker marker){
        Restaurant clickedRestaurant;
        LatLng clickedPos = marker.getPosition();

        for(int i = 0; i < listOfRestaurants.size(); i++){
            LatLng currentLatLng = new LatLng(listOfRestaurants.get(i).getLat(), listOfRestaurants.get(i).getLng());
            if(clickedPos.equals(currentLatLng)){
                return listOfRestaurants.get(i);
            }
        }
        return null;
    }

    private void addNewRestaurantMarkers(){
        mMap.clear();


        for (int j = 0;j < listOfRestaurants.size();j++) {
            LatLng nextRestaurant = new LatLng(listOfRestaurants.get(j).getLat(), listOfRestaurants.get(j).getLng());
            mMap.addMarker(new MarkerOptions().position(nextRestaurant).title(listOfRestaurants.get(j).getName()));
        }

        if(listOfRestaurants.size() > 0) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(listOfRestaurants.get(0).getLat(), listOfRestaurants.get(0).getLng())));
        }
    }

}
