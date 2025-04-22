package com.example.deremate_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;
import com.example.deremate_android.data.service.LoginService;
import com.example.deremate_android.data.model.LoginRequest;
import com.example.deremate_android.data.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;
import com.example.deremate_android.data.service.LoginService;
import com.example.deremate_android.data.model.LoginRequest;
import com.example.deremate_android.data.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText emailInput, passwordInput;
    Button loginButton, forgotPasswordButton;
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/") // Para emulador de Android Studio
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString();

                if (email.isEmpty()) {
                    emailInput.setError("Por favor ingresa un email");
                    return;
                }

                if (password.isEmpty()) {
                    passwordInput.setError("Por favor ingresa una contraseña");
                    return;
                }

                LoginRequest loginRequest = new LoginRequest(email, password);

                Call<LoginResponse> call = loginService.login(loginRequest);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                            passwordInput.setText("");
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(LoginActivity.this, PasswordRecoveryActivity.class);
                startActivity(intent3);
            }
        });
    }
}

// Validamos las credenciales (Memoria, luego se pasa a base de datps)
//if (email.equals("rodrigo_moens@hotmail.com") && password.equals("Felipe2011")) {
// Login exitoso - mostramos mensaje
//Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();

// MEJORA: Navegamos a la siguiente pantalla (MainActivity)
//Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//startActivity(intent);
//} else {
// Login fallido - mostramos mensaje de error
//Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
// MEJORA: Limpiamos el campo de contraseña por seguridad
//passwordInput.setText("");
//}

// Realizar la solicitud al backend
