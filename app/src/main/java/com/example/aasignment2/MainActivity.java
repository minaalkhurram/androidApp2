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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    Button addButton,filterbtn ;

    EditText searchtxt;
    public ArrayList<Restaurant> restaurantList;
    private SharedPreferences sharedPreferences;

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
                startActivity(intent);

                sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                String name = sharedPreferences.getString("name", "");
                String location = sharedPreferences.getString("location", "");
                String phone = sharedPreferences.getString("phone", "");
                String description = sharedPreferences.getString("description", "");


                Restaurant newRestaurant = new Restaurant(name, location, phone, description,0);


                restaurantList.add(newRestaurant);
                adapter.notifyDataSetChanged();




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

    private void addSampleData() {
        // Add sample restaurants to the list
        restaurantList.add(new Restaurant("The Wok", "Location 1", "1234567890", "Chinese food ",4));
        restaurantList.add(new Restaurant("Mandarin Kitchen", "Location 2", "0987654321", "Chinese foodt ",3.5f));
        restaurantList.add(new Restaurant("Pizza Hut", "Location 3", "4561237890", "Fresh Pizza!!",4.5f));
        restaurantList.add(new Restaurant("Bundu Khan", "Location 4", "9876543210", "Desi food cravings ",2));
        restaurantList.add(new Restaurant("Sollis ", "Location 5", "6549873210", "Italian pizza ",3));
        restaurantList.add(new Restaurant("Third Culture ", "Location 6", "3216549870", "Best coffee in town ",5));
    }

    public void init()
    {
        filterbtn=findViewById(R.id.fltrbtn);
        searchtxt=findViewById(R.id.searchTxt);
        addButton = findViewById(R.id.addBtn);

    }

}