package com.example.aasignment2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private ArrayList<Restaurant> restaurantList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addSampleData() {
        // Add sample restaurants to the list
        restaurantList.add(new Restaurant("Restaurant 1", "Location 1", "1234567890", "Description 1"));
        restaurantList.add(new Restaurant("Restaurant 2", "Location 2", "0987654321", "Description 2"));
        restaurantList.add(new Restaurant("Restaurant 3", "Location 3", "4561237890", "Description 3"));
        restaurantList.add(new Restaurant("Restaurant 4", "Location 4", "9876543210", "Description 4"));
        restaurantList.add(new Restaurant("Restaurant 5", "Location 5", "6549873210", "Description 5"));
        restaurantList.add(new Restaurant("Restaurant 6", "Location 6", "3216549870", "Description 6"));
    }
}