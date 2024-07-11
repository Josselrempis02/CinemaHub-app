package com.example.cinemahub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private Context context;
    private String userEmail;
    private String userName;

    public MovieAdapter(Context context, List<Movie> movies, String userEmail, String userName) {
        this.context = context;
        this.movies = movies;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.getTitle());
        List<String> genres = movie.getGenres();
        holder.genre1.setText(genres.size() > 0 ? genres.get(0) : "");
        holder.genre2.setText(genres.size() > 1 ? genres.get(1) : "");
        holder.genre3.setText(genres.size() > 2 ? genres.get(2) : "");
        holder.price.setText(movie.getPrice());
        holder.description.setText(movie.getDescription());
        Picasso.get().load(movie.getImageUrl()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingFragment bookingFragment = BookingFragment.newInstance(movie.getTitle(), userEmail, userName);
                if (context instanceof menu_navbar) {
                    ((menu_navbar) context).loadFragment(bookingFragment);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView genre1;
        TextView genre2;
        TextView genre3;
        TextView price;
        TextView description;
        ImageView image;

        MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            genre1 = itemView.findViewById(R.id.movie_genre1);
            genre2 = itemView.findViewById(R.id.movie_genre2);
            genre3 = itemView.findViewById(R.id.movie_genre3);
            price = itemView.findViewById(R.id.movie_price);
            description = itemView.findViewById(R.id.movie_description);
            image = itemView.findViewById(R.id.movie_image);
        }
    }
}
