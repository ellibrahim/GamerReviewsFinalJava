package com.example.gamerreviewsfinaljava;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Set default fragment when activity is created
        if (savedInstanceState == null) {
            loadFragment(new GamesFragment()); // Default fragment
        }

        // Set up listener for BottomNavigationView item selection
        bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment;
            int itemId = item.getItemId();  // FIX: assign itemId before using it
            if (itemId == R.id.nav_games) {
                selectedFragment = new GamesFragment();
            } else if (itemId == R.id.nav_recent_reviews) {
                selectedFragment = new RecentReviewsFragment();
            } else if (itemId == R.id.nav_my_reviews) {
                selectedFragment = new UserReviewsFragment();
            } else if (itemId == R.id.nav_settings) {
                selectedFragment = new SettingsFragment();
            } else {
                selectedFragment = new GamesFragment(); // default
            }

            loadFragment(selectedFragment);
            return true;
        });
    }

    // Method to load fragment in the FrameLayout container
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
