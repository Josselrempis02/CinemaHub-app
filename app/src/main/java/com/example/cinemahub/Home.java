package com.example.cinemahub;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public class Home extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private String userEmail;

    public Home() {
        // Required empty public constructor
    }

    public static Home newInstance(String userEmail) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString("USER_EMAIL", userEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userEmail = getArguments().getString("USER_EMAIL");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Movie> movies = Arrays.asList(
                new Movie("End Game", "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg", Arrays.asList("Action", "Adventure", "Sci-Fi"), "$15", "The final chapter of the Avengers saga."),
                new Movie("Movie 2", "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg", Arrays.asList("Drama", "Romance", "Thriller"), "$10", "An emotional drama about life and love."),
                new Movie("Movie 1", "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg", Arrays.asList("Comedy", "Family", "Fantasy"), "$8", "A hilarious comedy that will leave you in splits."),
                new Movie("Movie 2", "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg", Arrays.asList("Horror", "Mystery", "Thriller"), "$12", "A terrifying horror story."),
                new Movie("Movie 1", "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg", Arrays.asList("Sci-Fi", "Adventure", "Action"), "$14", "A science fiction adventure.")
                // Add more movies here
        );

        movieAdapter = new MovieAdapter(getActivity(), movies, userEmail);
        recyclerView.setAdapter(movieAdapter);

        return view;
    }
}
