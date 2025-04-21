package com.example.deremate_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;

public class PasswordRecoveryActivity extends AppCompatActivity{
    EditText emailInput;
    Button sendEmailButton;

    Button returnToLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordrecovery); // Cargamos el diseño XML del Login
        // Conectamos nuestras variables con los elementos del XML mediante sus IDs
        emailInput = findViewById(R.id.emailInput);
        sendEmailButton = findViewById(R.id.sendEmailButton);
        returnToLoginButton = findViewById(R.id.returnToLoginButton);
        // Configuramos qué sucede cuando se hace clic en el botón de login
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenemos el texto que el usuario escribió
                String email = emailInput.getText().toString().trim(); // trim() quita espacios en blanco
                // Validación básica - verificamos que no estén vacíos
                if (email.isEmpty()) {
                    emailInput.setError("Por favor ingresa un email");
                    return; // Detenemos la ejecución si hay error
                }
                // Validamos las credenciales (en un proyecto real, esto se haría contra una base de datos)
                if (email.equals("rodrigo_moens@hotmail.com")){
                    Intent intent = new Intent(PasswordRecoveryActivity.this, SendNotificationActivity.class);
                    startActivity(intent);
                }
                else {
                    // Falla - mostramos mensaje de error
                    Toast.makeText(PasswordRecoveryActivity.this, "Email Incorrecto", Toast.LENGTH_SHORT).show();
                    // MEJORA: Limpiamos el campo de contraseña por seguridad
                    emailInput.setText("");
                }
            }
        });
        returnToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswordRecoveryActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // This will close the current activity
            }
        });
    }
}
