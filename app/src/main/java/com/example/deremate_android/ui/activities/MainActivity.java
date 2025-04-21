package com.example.deremate_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button loginButton, registerButton;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); //Cargamos el diseño de Activity
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent Intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(Intent1);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent Intent2 = new Intent(MainActivity.this, com.example.deremate_android.ui.activities.RegisterActivity.class);
                startActivity(Intent2);
            }
        });
    }
}
