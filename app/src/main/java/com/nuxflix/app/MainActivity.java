package com.nuxflix.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Movie featuredMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Movie> movies = new ArrayList<>();

        // فيلم تجريبي لاختبار التطبيق
        featuredMovie = new Movie(
                "Big Buck Bunny",
                "فيلم رسوم متحركة قصير عن أرنب ضخم وديع ينتقم من ثلاثة قوارض مشاغبة، بطريقة مرحة ومليئة بالحيوية. تم إضافته هنا كفيلم تجريبي لاختبار مشغل الفيديو داخل تطبيق NuxFlix.",
                "2026",
                "10 د",
                "8.5",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                "https://upload.wikimedia.org/wikipedia/commons/c/c5/Big_buck_bunny_poster_big.jpg",
                Color.parseColor("#8B0000")
        );

        movies.add(featuredMovie);

        TextView heroTitle = findViewById(R.id.hero_title);
        TextView heroMeta = findViewById(R.id.hero_meta);
        heroTitle.setText(featuredMovie.title);
        heroMeta.setText(featuredMovie.year + " · " + featuredMovie.duration + " · ⭐ " + featuredMovie.rating);

        Button btnPlay = findViewById(R.id.btn_play);
        Button btnInfo = findViewById(R.id.btn_info);

        btnPlay.setOnClickListener(v -> openPlayer(featuredMovie));
        btnInfo.setOnClickListener(v -> openPlayer(featuredMovie));

        RecyclerView recyclerView = findViewById(R.id.movies_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MovieAdapter(movies));
    }

    private void openPlayer(Movie movie) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra("title", movie.title);
        intent.putExtra("description", movie.description);
        intent.putExtra("videoUrl", movie.videoUrl);
        startActivity(intent);
    }
}
