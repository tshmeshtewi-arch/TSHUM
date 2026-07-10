package com.nuxflix.app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(
    private var movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { onItemClick(movie) }
    }

    override fun getItemCount() = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val posterView: ImageView = itemView.findViewById(R.id.poster_view)
        private val titleView: TextView = itemView.findViewById(R.id.movie_title)
        private val metaView: TextView = itemView.findViewById(R.id.movie_meta)
        private val descView: TextView = itemView.findViewById(R.id.movie_description)

        fun bind(movie: Movie) {
            titleView.text = movie.title
            metaView.text = "${movie.year} • ${movie.duration} • ${movie.rating}"
            descView.text = movie.description

            Glide.with(itemView.context)
                .load(movie.posterUrl)
                .placeholder(android.R.color.darker_gray)
                .centerCrop()
                .into(posterView)
        }
    }
}