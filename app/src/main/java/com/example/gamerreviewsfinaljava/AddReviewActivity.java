package com.example.gamerreviewsfinaljava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddReviewActivity extends AppCompatActivity {
    private TextView textViewGameName;
    private EditText editTextReview;
    private RatingBar ratingBar;
    private Button buttonSubmit;

    private String gameId;
    private String gameName;

    private FirebaseFirestore db;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        gameId = getIntent().getStringExtra("gameId");
        gameName = getIntent().getStringExtra("gameName");

        textViewGameName = findViewById(R.id.textViewAddReviewGameName);
        editTextReview = findViewById(R.id.editTextReview);
        ratingBar = findViewById(R.id.ratingBar);
        buttonSubmit = findViewById(R.id.buttonSubmitReview);

        textViewGameName.setText("Review for: " + (gameName != null ? gameName : gameId));

        buttonSubmit.setOnClickListener(v -> submitReview());
    }

    private void submitReview() {
        String reviewText = editTextReview.getText().toString().trim();
        int ratingValue = (int) ratingBar.getRating();

        if (reviewText.isEmpty()) {
            Toast.makeText(this, "Please write a review.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (currentUser == null) {
            Toast.makeText(this, "You must be logged in to add a review.", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = currentUser.getEmail();
        String username = "Anonymous";
        if (email != null && email.contains("@")) {
            username = email.substring(0, email.indexOf("@"));
        }

        Map<String, Object> review = new HashMap<>();
        review.put("reviewId", db.collection("reviews").document().getId());
        review.put("gameId", gameId);
        review.put("gameName", gameName); // <-- Now correctly saved
        review.put("userId", currentUser.getUid());
        review.put("userName", username);
        review.put("reviewText", reviewText);
        review.put("rating", ratingValue);
        review.put("timestamp", System.currentTimeMillis());

        db.collection("reviews")
                .add(review)
                .addOnSuccessListener(docRef -> {
                    Toast.makeText(this, "Review added!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
