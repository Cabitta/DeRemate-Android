package com.example.deremate_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;
import com.example.deremate_android.data.api.ApiClient;
import com.example.deremate_android.data.model.VerifyRequest;
import com.example.deremate_android.data.model.VerifyResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationActivity extends AppCompatActivity {

    private EditText codeEditText;
    private Button verifyButton;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        codeEditText = findViewById(R.id.edit_verification_code);
        verifyButton = findViewById(R.id.btn_verify);

        // Obtenemos el email del intent
        email = getIntent().getStringExtra("email");

        verifyButton.setOnClickListener(v -> attemptVerification());
    }

    private void attemptVerification() {
        codeEditText.setError(null);
        String code = codeEditText.getText().toString().trim();

        if (code.isEmpty()) {
            codeEditText.setError("Ingrese el código de verificación");
            codeEditText.requestFocus();
            return;
        }

        verifyAccount(email, code);
    }

    private void verifyAccount(String email, String code) {
        VerifyRequest request = new VerifyRequest(email, code);
        ApiClient.getVerifyAccountService().verifyAccount(request).enqueue(new Callback<VerifyResponse>() {
            @Override
            public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(VerificationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    // Redirigir a LoginActivity después de verificación exitosa
                    Intent intent = new Intent(VerificationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    try {
                        JSONObject errorBody = new JSONObject(response.errorBody().string());
                        String errorMessage = errorBody.getString("message");
                        Toast.makeText(VerificationActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(VerificationActivity.this, "Error en la verificación", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<VerifyResponse> call, Throwable t) {
                Toast.makeText(VerificationActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}