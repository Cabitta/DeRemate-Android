package com.example.deremate_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;
import com.example.deremate_android.data.model.Delivery;
import com.example.deremate_android.data.model.RegisterResponse;
import com.example.deremate_android.data.repository.DeliveryRepository;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    
    private EditText nameEditText;
    private EditText lastnameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button registerButton;
    private ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        nameEditText = findViewById(R.id.edit_name);
        lastnameEditText = findViewById(R.id.edit_lastname);
        emailEditText = findViewById(R.id.edit_email);
        passwordEditText = findViewById(R.id.edit_password);
        confirmPasswordEditText = findViewById(R.id.edit_confirm_password);
        registerButton = findViewById(R.id.btn_register);
        backArrow = findViewById(R.id.back_arrow);
        
        backArrow.setOnClickListener(v -> onBackPressed());
        
        registerButton.setOnClickListener(v -> attemptRegister());
    }
    
    private void attemptRegister() {
        // Reset errors
        nameEditText.setError(null);
        lastnameEditText.setError(null);
        emailEditText.setError(null);
        passwordEditText.setError(null);
        confirmPasswordEditText.setError(null);
        
        String name = nameEditText.getText().toString().trim();
        String lastname = lastnameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        
        boolean cancel = false;
        View focusView = null;
        
        if (name.isEmpty()) {
            nameEditText.setError("Ingrese su nombre");
            focusView = nameEditText;
            cancel = true;
        }
        
        if (lastname.isEmpty()) {
            lastnameEditText.setError("Ingrese su apellido");
            if (focusView == null) focusView = lastnameEditText;
            cancel = true;
        }
        
        if (email.isEmpty()) {
            emailEditText.setError("Ingrese su email");
            if (focusView == null) focusView = emailEditText;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailEditText.setError("Email inválido");
            if (focusView == null) focusView = emailEditText;
            cancel = true;
        }
        
        if (password.isEmpty()) {
            passwordEditText.setError("Ingrese una contraseña");
            if (focusView == null) focusView = passwordEditText;
            cancel = true;
        }
        
        if (confirmPassword.isEmpty()) {
            confirmPasswordEditText.setError("Confirme su contraseña");
            if (focusView == null) focusView = confirmPasswordEditText;
            cancel = true;
        } else if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Las contraseñas no coinciden");
            if (focusView == null) focusView = confirmPasswordEditText;
            cancel = true;
        }
        
        if (cancel) {
            // There was an error; focus the first form field with an error
            if (focusView != null) {
                focusView.requestFocus();
            }
        } else {
            registerUser(name, lastname, email, password, confirmPassword);
        }
    }
    
    private void registerUser(String name, String lastname, String email, String password, String confrimPassword) {

        Delivery deliveryAccount = new Delivery(name, lastname, email, password, confrimPassword);
        DeliveryRepository deliveryRepository = new DeliveryRepository();

        deliveryRepository.registerDelivery(deliveryAccount, new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    // Redirigir a VerificationActivity
                    Intent intent = new Intent(RegisterActivity.this, VerificationActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                    finish();
                } else {
                    try {
                        String errorString = response.errorBody().string();
                        Log.e(TAG, "❌ Error body: " + errorString);
                        JSONObject errorBody = new JSONObject(errorString);
                        String errorMessage = errorBody.getString("message");
                        Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
    }
    
    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "⭐ onStart: La Activity está a punto de hacerse visible");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "⭐ onResume: La Activity es visible y tiene el foco");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "⭐ onPause: La Activity está perdiendo el foco");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "⭐ onStop: La Activity ya no es visible");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "⭐ onRestart: La Activity está volviendo a empezar después de detenerse");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "⭐ onDestroy: La Activity está siendo destruida");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "⭐ onSaveInstanceState: Guardando el estado de la Activity");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "⭐ onRestoreInstanceState: Restaurando el estado guardado de la Activity");
    }
}