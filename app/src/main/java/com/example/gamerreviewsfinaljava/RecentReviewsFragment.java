package com.example.gamerreviewsfinaljava;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecentReviewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;
    private List<Game> gameList; // List of all games
    private FirebaseFirestore db;

    public RecentReviewsFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent_reviews, container, false);

        recyclerView = view.findViewById(R.id.recent_reviews_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        reviewList = new ArrayList<>();

        // Initialize gameList from the Game class
        gameList = Game.getGame();  // Fetch the list of games from the Game class

        if (gameList == null || gameList.isEmpty()) {
            // If gameList is null or empty, throw an exception to help debug.
            throw new NullPointerException("Game list is null or empty! Please check the Game.getGame() method.");
        }

        // Pass both reviewList and gameList to the adapter
        reviewAdapter = new ReviewAdapter(reviewList, gameList, true);  // true = Show image
        recyclerView.setAdapter(reviewAdapter);

        db = FirebaseFirestore.getInstance();

        // Load reviews from Firebase
        loadRecentReviews();

        return view;
    }

    private void loadRecentReviews() {
        CollectionReference reviewsRef = db.collection("reviews");


        reviewsRef.orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    reviewList.clear();
                    if (!queryDocumentSnapshots.isEmpty()) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Review review = document.toObject(Review.class);

                            // Log the username to ensure it's being fetched
                            Log.d("RecentReviewsFragment", "Fetched username: " + review.getUsername());

                            reviewList.add(review);
                        }
                        reviewAdapter.notifyDataSetChanged();  // Notify the adapter to update the UI
                    } else {
                        Toast.makeText(getContext(), "No reviews yet.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Failed to load reviews: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
