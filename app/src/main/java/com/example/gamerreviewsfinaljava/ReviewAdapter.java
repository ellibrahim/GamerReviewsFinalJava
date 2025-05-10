package com.example.gamerreviewsfinaljava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> reviewList;
    private List<Game> gameList; // List of games (passed from the activity/fragment)
    private boolean showImage;  // Flag to control whether to show image

    public ReviewAdapter(List<Review> reviewList, List<Game> gameList, boolean showImage) {
        this.reviewList = reviewList;
        this.gameList = gameList;
        this.showImage = showImage; // Initialize the showImage flag
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);

        holder.usernameTextView.setText(review.getUsername());
        holder.gameNameTextView.setText(review.getGameName());
        holder.reviewTextView.setText(review.getReviewText());
        holder.timestampTextView.setText(formatTimestamp(review.getTimestamp()));
        holder.ratingBar.setRating(review.getRating());

        if (showImage) {
            // Find the correct game image based on game name
            Game game = findGameByName(review.getGameName());  // Find game by name
            if (game != null) {
                // Set the real game image from drawable resources
                holder.gameImageView.setImageResource(game.getImageResId());
            } else {
                // If game not found, set placeholder image
                holder.gameImageView.setImageResource(R.drawable.placeholder_image);
            }
            holder.gameImageView.setVisibility(View.VISIBLE);  // Show image
        } else {
            holder.gameImageView.setVisibility(View.GONE);  // Hide image
        }
    }

    private Game findGameByName(String gameName) {
        // This function searches for the game based on its name in gameList
        for (Game game : gameList) {
            if (game.getName().equalsIgnoreCase(gameName)) {
                return game;
            }
        }
        return null;  // Return null if no game found
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView, gameNameTextView, reviewTextView, timestampTextView;
        ImageView gameImageView;
        RatingBar ratingBar;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.text_username);
            gameNameTextView = itemView.findViewById(R.id.text_game_name);
            reviewTextView = itemView.findViewById(R.id.text_review);
            timestampTextView = itemView.findViewById(R.id.text_timestamp);
            gameImageView = itemView.findViewById(R.id.image_game);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}

