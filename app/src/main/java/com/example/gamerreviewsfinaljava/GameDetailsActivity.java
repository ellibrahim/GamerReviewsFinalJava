package com.example.gamerreviewsfinaljava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class GameDetailsActivity extends AppCompatActivity {

    private TextView gameNameTextView;
    private TextView gameDescriptionTextView;
    private TextView gameReleaseDateTextView;
    private TextView gameDeveloperTextView;
    private TextView gamePublisherTextView;
    private ImageView gameImageView;
    private FloatingActionButton fabAddReview;
    private RecyclerView recyclerViewReviews;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewsList;
    private FirebaseFirestore db;
    private String gameId;
    private List<Game> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        // Initialize the views
        gameNameTextView = findViewById(R.id.textViewGameName);
        gameDescriptionTextView = findViewById(R.id.textViewGameDescription);
        gameReleaseDateTextView = findViewById(R.id.textViewGameReleaseDate);
        gameDeveloperTextView = findViewById(R.id.textViewGameDeveloper);
        gamePublisherTextView = findViewById(R.id.textViewGamePublisher);
        gameImageView = findViewById(R.id.imageViewGame);
        fabAddReview = findViewById(R.id.fabAddReview);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);

        // Set up RecyclerView for reviews
        recyclerViewReviews.setLayoutManager(new LinearLayoutManager(this));
        reviewsList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(reviewsList, gameList, false);  // true = Show image
        recyclerViewReviews.setAdapter(reviewAdapter);

        db = FirebaseFirestore.getInstance();

        // Get game details from the intent
        Game game = getIntent().getParcelableExtra("game");

        if (game != null) {
            // Set game details to the views
            gameId = game.getTitle();  // Or use game.getId() if you have an ID field in Game class
            gameNameTextView.setText(game.getName());
            gameDescriptionTextView.setText(game.getDescription());
            gameReleaseDateTextView.setText(game.getReleaseDate());
            gameDeveloperTextView.setText(game.getDeveloper());
            gamePublisherTextView.setText(game.getPublisher());

            // Set the game image
            gameImageView.setImageResource(game.getImageResId());

            // Fetch reviews from Firestore
            fetchReviews(gameId);
        }

        // FAB click listener: Navigate to AddReviewActivity
        fabAddReview.setOnClickListener(v -> {
            if (game != null) {
                Intent intent = new Intent(GameDetailsActivity.this, AddReviewActivity.class);
                intent.putExtra("gameId", gameId);    // Pass game ID
                intent.putExtra("gameName", game.getName());
                startActivity(intent);
            }
        });
    }

    private void fetchReviews(String gameId) {
        // Fetch reviews for the selected game from Firestore
        db.collection("reviews")
                .whereEqualTo("gameId", gameId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        reviewsList.clear();  // Clear previous reviews
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Review review = document.toObject(Review.class);
                            reviewsList.add(review);  // Add reviews to the list
                        }
                        reviewAdapter.notifyDataSetChanged();  // Notify adapter to update the UI

                        if (reviewsList.isEmpty()) {
                            Toast.makeText(GameDetailsActivity.this, "No reviews yet for this game.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(GameDetailsActivity.this, "Failed to load reviews", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
