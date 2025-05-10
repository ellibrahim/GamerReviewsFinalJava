package com.example.gamerreviewsfinaljava;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class TestFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment); // create this XML below

        // Load GamesFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new GamesFragment())
                .commit();
    }
}
