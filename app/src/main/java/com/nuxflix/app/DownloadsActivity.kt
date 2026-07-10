package com.nuxflix.app

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DownloadsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_downloads)

        val btnBack: ImageButton = findViewById(R.id.btn_downloads_back)
        val emptyText: TextView = findViewById(R.id.downloads_empty)

        btnBack.setOnClickListener { finish() }
        emptyText.visibility = TextView.VISIBLE
    }
}