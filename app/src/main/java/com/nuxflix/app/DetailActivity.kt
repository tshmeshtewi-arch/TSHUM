package com.nuxflix.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movieId = intent.getIntExtra("movie_id", -1)
        val movie = MovieRepository.getMovieById(movieId)
        if (movie == null) {
            finish()
            return
        }

        val heroImage: ImageView = findViewById(R.id.hero_image)
        val btnBack: ImageButton = findViewById(R.id.btn_back)
        val titleText: TextView = findViewById(R.id.detail_title)
        val yearText: TextView = findViewById(R.id.detail_year)
        val durationText: TextView = findViewById(R.id.detail_duration)
        val ratingText: TextView = findViewById(R.id.detail_rating)
        val descText: TextView = findViewById(R.id.detail_description)
        val btnPlay: Button = findViewById(R.id.btn_play)
        val btnTrailer: Button = findViewById(R.id.btn_trailer)

        titleText.text = movie.title
        yearText.text = movie.year
        durationText.text = movie.duration
        ratingText.text = movie.rating
        descText.text = movie.description

        Glide.with(this)
            .load(movie.posterUrl)
            .placeholder(android.R.color.darker_gray)
            .centerCrop()
            .into(heroImage)

        btnBack.setOnClickListener { finish() }

        btnPlay.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra("videoUrl", movie.videoUrl)
            intent.putExtra("title", movie.title)
            startActivity(intent)
        }

        btnTrailer.setOnClickListener {
            val url = if (movie.videoUrlBackup.isNotBlank()) movie.videoUrlBackup else movie.videoUrl
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra("videoUrl", url)
            intent.putExtra("title", movie.title + " - Trailer")
            startActivity(intent)
        }
    }
}