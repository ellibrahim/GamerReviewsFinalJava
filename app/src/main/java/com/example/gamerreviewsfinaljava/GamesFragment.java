package com.example.gamerreviewsfinaljava;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GamesFragment extends Fragment {

    private RecyclerView recyclerView;
    private GamesAdapter adapter;
    private List<Game> gamesList;

    public GamesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_games, container, false);

        // Set up RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewGames);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Load games from static method
        gamesList = Game.getGame();

        // Set adapter with click listener
        adapter = new GamesAdapter(gamesList, game -> {
            Intent intent = new Intent(getActivity(), GameDetailsActivity.class);
            intent.putExtra("game", game);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
