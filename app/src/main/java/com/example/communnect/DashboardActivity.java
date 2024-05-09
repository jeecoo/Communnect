package com.example.communnect;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Home");
        }

        // Initialize views
        BottomNavigationView navigationView = findViewById(R.id.navigations);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    actionBar.setTitle("Home");
                    HomeFragment fragment1 = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment1, "").commit();
                    return true;
                } else if (item.getItemId() == R.id.nav_services) {
                    actionBar.setTitle("Services");
                    ServicesFragment fragment2 = new ServicesFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment2, "").commit();
                    return true;
                } else if (item.getItemId() == R.id.nav_profile) {
                    actionBar.setTitle("Profile");
                    ProfileFragment fragment3 = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment3, "").commit();
                    return true;
                }
                return false;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() {
            @NonNull
            @Override
            public WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });
    }
}
