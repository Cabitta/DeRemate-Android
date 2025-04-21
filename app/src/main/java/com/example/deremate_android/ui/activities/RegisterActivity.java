package com.example.deremate_android.ui.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView messageText = findViewById(R.id.infoText);
        messageText.setText("Esta parte esta en progreso. Lo mejor se hace esperar!!!");
    }
}