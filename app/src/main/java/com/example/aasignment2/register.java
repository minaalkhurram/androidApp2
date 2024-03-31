package com.example.aasignment2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class register extends AppCompatActivity {

    Button regBtn;
    EditText name, phone, location, description,rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        init();


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Store data in SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("Prefs_new", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", name.getText().toString());
                editor.putString("location", location.getText().toString());
                editor.putString("phone", phone.getText().toString());
                editor.putString("description",description.getText().toString());
                editor.putString("ratings", rating.getText().toString());
                editor.apply();





                Toast.makeText(getApplicationContext(), "New Restaurant Added !", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void init()
    {




        regBtn=findViewById(R.id.registerBtn);
        name=findViewById(R.id.nameTxt);
        phone=findViewById(R.id.phoneTxt);
        location=findViewById(R.id.locTxt);
        description=findViewById(R.id.descTxt);
        rating=findViewById(R.id.ratingTxt);



    }
}