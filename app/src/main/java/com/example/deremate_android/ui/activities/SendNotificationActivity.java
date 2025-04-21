package com.example.deremate_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;

public class SendNotificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button returnToLoginButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);

        TextView messageText = findViewById(R.id.confirmationText);
        messageText.setText("¡Te hemos enviado un email para recuperar tu contraseña!");

        returnToLoginButton = findViewById(R.id.returnToLoginButton);
        returnToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendNotificationActivity.this, LoginActivity.class);
                // Clear the activity stack so the user can't go back to the password recovery screens
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}