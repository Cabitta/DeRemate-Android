package com.example.deremate_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;
import com.example.deremate_android.data.service.ForgotPasswordService;
import com.example.deremate_android.data.model.ForgotPasswordRequest;
import com.example.deremate_android.data.model.ForgotPasswordResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPasswordActivity extends AppCompatActivity{
    EditText emailInput;
    Button sendEmailButton;
    Button returnToLoginButton;
    private ForgotPasswordService forgotPasswordService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/") // Para emulador de Android Studio
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        forgotPasswordService = retrofit.create(ForgotPasswordService.class);
        // Cargamos el diseño XML del Login
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
                ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest(email);
                Call<ForgotPasswordResponse> call = forgotPasswordService.forgotpassword(forgotPasswordRequest);
                call.enqueue(new Callback<ForgotPasswordResponse>() {
                    @Override
                    public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(ForgotPasswordActivity.this, "Email enviado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPasswordActivity.this, SendNotificationActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(ForgotPasswordActivity.this, "El email no es correcto", Toast.LENGTH_SHORT).show();
                            emailInput.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                        Toast.makeText(ForgotPasswordActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                // Validamos las credenciales (en un proyecto real, esto se haría contra una base de datos)
                //if (email.equals("rodrigo_moens@hotmail.com")){
                //  Intent intent = new Intent(PasswordRecoveryActivity.this, SendNotificationActivity.class);
                //startActivity(intent);
                //}
                //else {
                // Falla - mostramos mensaje de error
                //  Toast.makeText(PasswordRecoveryActivity.this, "Email Incorrecto", Toast.LENGTH_SHORT).show();
                // MEJORA: Limpiamos el campo de contraseña por seguridad
                //emailInput.setText("");
                //}
            }
        });
        returnToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent3);// This will close the current activity
            }
        });
    }
}
