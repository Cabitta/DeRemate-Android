package com.example.deremate_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.deremate_android.R;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.deremate_android.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    Button btnDeliveryHistory;

    Button btnAvailableRoutes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        btnDeliveryHistory = findViewById(R.id.btn_delivery_history);

        btnDeliveryHistory.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, DeliveryHistoryActivity.class);
            startActivity(intent);
        });

        btnAvailableRoutes = findViewById(R.id.btn_available_routes);

        btnAvailableRoutes.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AvailableRoutesActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}