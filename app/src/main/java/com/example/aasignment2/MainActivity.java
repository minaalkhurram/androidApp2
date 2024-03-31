package com.example.aasignment2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    Button addButton,filterbtn ;

    private static final int REQUEST_CODE_REGISTER_ACTIVITY = 1;

    EditText searchtxt;
    Context context=this;
    public ArrayList<Restaurant> restaurantList;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "restaurant_preferences";
    private static final String KEY_RESTAURANTS = "restaurants";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        init();

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize restaurant list
        restaurantList = new ArrayList<>();

        // Add sample data
        addSampleData();

        // Initialize adapter
        adapter = new RestaurantAdapter(this, restaurantList);

        // Set adapter to RecyclerView
        recyclerView.setAdapter(adapter);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,register.class);

                startActivityForResult(intent, REQUEST_CODE_REGISTER_ACTIVITY);


            }
        });

        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String minText = searchtxt.getText().toString();
                float min = Float.parseFloat(minText);

                adapter.filterByRating(min);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_REGISTER_ACTIVITY) {
            updateDataSet();
        }
    }

    private void addSampleData() {
        // Add sample restaurants to the list
        restaurantList=getRestaurants(this);
      //  saveRestaurants(this,restaurantList);
    }

    private void updateDataSet() {
        // Retrieve data from SharedPreferences
        sharedPreferences = getSharedPreferences("Prefs_new", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String location = sharedPreferences.getString("location", "");
        String phone = sharedPreferences.getString("phone", "");
        String description = sharedPreferences.getString("description", "");
        String rating=sharedPreferences.getString("ratings","");



        // Create new Restaurant object and add to list
        Restaurant newRestaurant = new Restaurant(name, location, phone, description,  Float.parseFloat(rating));
        restaurantList.add(newRestaurant);

        // Notify adapter of dataset change
        adapter.notifyDataSetChanged();

        // Save updated restaurants list
        saveRestaurants(context, restaurantList);
    }

    public static void saveRestaurants(Context context, List<Restaurant> restaurants) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> restaurantSet = new HashSet<>();
        for (Restaurant restaurant : restaurants) {
            String restaurantString = restaurant.getName() + "," +
                    restaurant.getLoc() + "," +
                    restaurant.getPhone() + "," +
                    restaurant.getDesc() + "," +
                    restaurant.getRatings();
            restaurantSet.add(restaurantString);
        }

        editor.putStringSet(KEY_RESTAURANTS, restaurantSet);
        editor.apply();
    }


    public static ArrayList<Restaurant> getRestaurants(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> restaurantSet = sharedPreferences.getStringSet(KEY_RESTAURANTS, null);

        ArrayList<Restaurant> restaurants = new ArrayList<>();
        if (restaurantSet != null) {
            for (String restaurantString : restaurantSet) {
                String[] restaurantData = restaurantString.split(",");
                if (restaurantData.length == 5) {
                    String name = restaurantData[0];
                    String location = restaurantData[1];
                    String phone = restaurantData[2];
                    String description = restaurantData[3];
                    float rating = Float.parseFloat(restaurantData[4]);

                    Restaurant restaurant = new Restaurant(name, location, phone, description, rating);
                    restaurants.add(restaurant);
                }
            }
        }

        return restaurants;
    }


    public void init()
    {
        filterbtn=findViewById(R.id.fltrbtn);
        searchtxt=findViewById(R.id.searchTxt);
        addButton = findViewById(R.id.addBtn);

    }

}