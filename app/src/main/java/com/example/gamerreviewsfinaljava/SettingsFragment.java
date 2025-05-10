package com.example.gamerreviewsfinaljava;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsFragment extends Fragment {

    private Button btnChangePassword, btnLogout, btnLogin, btnSignUp;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        // Initialize buttons
        btnChangePassword = rootView.findViewById(R.id.btnChangePassword);
        btnLogout = rootView.findViewById(R.id.btnLogout);
        btnLogin = rootView.findViewById(R.id.btnLogin);
        btnSignUp = rootView.findViewById(R.id.btnSignUp);

        // Check if the user is logged in or not
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        updateUI(user);

        // Handle "Change Password" button click
        btnChangePassword.setOnClickListener(v -> {
            if (user != null) {
                sendPasswordResetEmail(user.getEmail());
            } else {
                Toast.makeText(getContext(), "You must be logged in to change password.", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle "Logout" button click
        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            updateUI(null); // Update UI immediately after logout
            Toast.makeText(getContext(), "Logged out", Toast.LENGTH_SHORT).show();
        });

        // Handle "Log In" button click
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        });

        // Handle "Sign Up" button click
        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SignUpActivity.class);
            startActivity(intent);
        });

        return rootView;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // User is logged in
            btnLogout.setVisibility(View.VISIBLE);
            btnChangePassword.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
            btnSignUp.setVisibility(View.GONE);
        } else {
            // User is not logged in
            btnLogout.setVisibility(View.GONE);
            btnChangePassword.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
            btnSignUp.setVisibility(View.VISIBLE);
        }
    }

    private void sendPasswordResetEmail(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "Password reset email sent.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
