package com.example.gamerreviewsfinaljava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GameViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Game game);
    }

    private List<Game> gamesList;
    private OnItemClickListener listener;

    public GamesAdapter(List<Game> gamesList, OnItemClickListener listener) {
        this.gamesList = gamesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Game game = gamesList.get(position);
        holder.nameTextView.setText(game.getTitle());
        holder.descriptionTextView.setText(game.getDescription());
        holder.imageView.setImageResource(game.getImageResId());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(game));
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    static class GameViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView;
        ImageView imageView;

        GameViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewGameName);
            descriptionTextView = itemView.findViewById(R.id.textViewGameDescription);
            imageView = itemView.findViewById(R.id.imageViewGame);
        }
    }
}
