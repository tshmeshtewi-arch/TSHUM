package com.nuxflix.app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.title);
        holder.meta.setText(movie.year + " · " + movie.duration + " · ⭐ " + movie.rating);
        holder.poster.setBackgroundColor(movie.posterColor);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), PlayerActivity.class);
            intent.putExtra("title", movie.title);
            intent.putExtra("description", movie.description);
            intent.putExtra("videoUrl", movie.videoUrl);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, meta;
        View poster;

        MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            meta = itemView.findViewById(R.id.movie_meta);
            poster = itemView.findViewById(R.id.poster_view);
        }
    }
}
