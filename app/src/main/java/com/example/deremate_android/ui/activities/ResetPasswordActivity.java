package com.example.deremate_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;
import com.example.deremate_android.data.model.ResetPasswordRequest;
import com.example.deremate_android.data.model.ResetPasswordResponse;
import com.example.deremate_android.data.service.ResetPasswordService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResetPasswordActivity extends AppCompatActivity{
    EditText codeInput;
    EditText newPasswordInput;
    EditText confirmPasswordInput;
    Button sendInformationButton;
    Button returnToSendNotificationButton;

    private ResetPasswordService resetPasswordService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmationcode);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/") // Para emulador de Android Studio
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        resetPasswordService = retrofit.create(ResetPasswordService.class);
        codeInput = findViewById(R.id.codeInput);
        newPasswordInput = findViewById(R.id.newPasswordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        sendInformationButton = findViewById(R.id.sendInformationButton);
        returnToSendNotificationButton = findViewById(R.id.sendNotificationButton);
        // Configuramos qué sucede cuando se hace clic en el botón de envio de codigo
        sendInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codeInput.getText().toString().trim();
                String newPassword = newPasswordInput.getText().toString().trim();
                String confirmPassword = confirmPasswordInput.getText().toString().trim();
                // Validación básica - verificamos que no estén vacíos
                if (code.isEmpty()) {
                    codeInput.setError("Por favor ingresa un codigo");
                    return; // Detenemos la ejecución si hay error
                }
                if (newPassword.isEmpty()) {
                    newPasswordInput.setError("Por favor ingresa la nueva contraseña");
                    return; // Detenemos la ejecución si hay error
                }
                if (confirmPassword.isEmpty()) {
                    confirmPasswordInput.setError("Por favor ingresa la nueva contraseña");
                    return; // Detenemos la ejecución si hay error
                }
                ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest(code, newPassword, confirmPassword);
                Call<ResetPasswordResponse> call = resetPasswordService.resetearpassword(resetPasswordRequest);
                call.enqueue(new Callback<ResetPasswordResponse>() {
                    @Override
                    public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(ResetPasswordActivity.this, "Contraseña modificada", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(ResetPasswordActivity.this, "El codigo no es correcto o las contraseñas no coinciden o la contraseña no es segura", Toast.LENGTH_SHORT).show();
                            codeInput.setText("");
                        }
                    }
                    @Override
                    public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                        Toast.makeText(ResetPasswordActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        returnToSendNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(ResetPasswordActivity.this, SendNotificationActivity.class);
                startActivity(intent3);// This will close the current activity
            }
        });
    }
}