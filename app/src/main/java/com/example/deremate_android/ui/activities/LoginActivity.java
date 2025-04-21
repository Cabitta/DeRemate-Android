package com.example.deremate_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;

public class LoginActivity extends AppCompatActivity {
    // Declaramos las variables para nuestros elementos de UI
    EditText emailInput, passwordInput;
    Button loginButton, forgotPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Cargamos el diseño XML del Login

        // Conectamos nuestras variables con los elementos del XML mediante sus IDs
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        forgotPasswordButton = findViewById((R.id.forgotPasswordButton));

        // Configuramos qué sucede cuando se hace clic en el botón de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenemos el texto que el usuario escribió
                String email = emailInput.getText().toString().trim(); // trim() quita espacios en blanco
                String password = passwordInput.getText().toString();

                // Validación básica - verificamos que no estén vacíos
                if (email.isEmpty()) {
                    emailInput.setError("Por favor ingresa un email");
                    return; // Detenemos la ejecución si hay error
                }

                if (password.isEmpty()) {
                    passwordInput.setError("Por favor ingresa una contraseña");
                    return;
                }
                // Validamos las credenciales (Memoria, luego se pasa a base de datps)
                if (email.equals("rodrigo_moens@hotmail.com") && password.equals("Felipe2011")) {
                    // Login exitoso - mostramos mensaje
                    Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();

                    // MEJORA: Navegamos a la siguiente pantalla (MainActivity)
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    // Login fallido - mostramos mensaje de error
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    // MEJORA: Limpiamos el campo de contraseña por seguridad
                    passwordInput.setText("");
                }
            }
        });
        // Configuramos qué sucede cuando se hace clic en el botón de "Olvidé mi contraseña"
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegamos a la pantalla de recuperación de contraseña
                Intent intent3 = new Intent(LoginActivity.this, PasswordRecoveryActivity.class);
                startActivity(intent3);
            }
        });
    }
}
