package com.example.gamerreviewsfinaljava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserReviewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;

    public UserReviewsFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_reviews, container, false);

        recyclerView = view.findViewById(R.id.user_reviews_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        reviewList = new ArrayList<>();

        // Get the list of games
        List<Game> gameList = Game.getGame();  // Fetch game list from Game class

        // Pass both reviewList and gameList to the adapter
        reviewAdapter = new ReviewAdapter(reviewList, gameList, true);  // true = Show image
        recyclerView.setAdapter(reviewAdapter);

        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            loadUserReviews(currentUser.getUid());
        } else {
            Toast.makeText(getContext(), "You must be logged in to view your reviews.", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void loadUserReviews(String userId) {
        CollectionReference reviewsRef = db.collection("reviews");

        reviewsRef.whereEqualTo("userId", userId)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    reviewList.clear();
                    if (!queryDocumentSnapshots.isEmpty()) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Review review = document.toObject(Review.class);
                            reviewList.add(review);
                        }
                        reviewAdapter.notifyDataSetChanged();  // Notify adapter to update the UI
                    } else {
                        Toast.makeText(getContext(), "No reviews found.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error loading reviews: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
