package com.example.deremate_android.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;
import com.example.deremate_android.ui.fragments.DeliveryListFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DeliveryHistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_history);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new DeliveryListFragment())
                    .commit();
        }
    }
}
