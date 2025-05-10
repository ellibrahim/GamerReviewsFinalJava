package com.example.gamerreviewsfinaljava;

import com.google.firebase.firestore.PropertyName;

public class Review {
    private String reviewId;
    private String userId;
    private String username;
    private String gameId;
    private String gameName;
    private String gameImageUrl; // NEW - Game picture
    private String reviewText;
    private int rating; // NEW - Review stars
    private long timestamp;

    // Required empty constructor for Firebase
    public Review() {}

    public Review(String reviewId, String userId, String username, String gameId, String gameName, String gameImageUrl, String reviewText, int rating, long timestamp) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.username = username;
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameImageUrl = gameImageUrl;
        this.reviewText = reviewText;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    // Getters
    public String getReviewId() {
        return reviewId;
    }

    public String getUserId() {
        return userId;
    }

    @PropertyName("userName") // This annotation ensures Firestore maps the field correctly to 'username'
    public String getUsername() {
        return username;
    }

    public String getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameImageUrl() {
        return gameImageUrl;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @PropertyName("userName") // Map Firestore 'userName' field to 'username' property
    public void setUsername(String username) {
        this.username = username;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameImageUrl(String gameImageUrl) {
        this.gameImageUrl = gameImageUrl;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
