package com.nuxflix.app

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val year: String,
    val duration: String,
    val rating: String,
    val videoUrl: String,
    val videoUrlBackup: String = "",
    val posterUrl: String,
    val posterColor: Int = 0xFF1A1A2E.toInt(),
    val category: String = "أفلام جديدة",
    val isFeatured: Boolean = false,
    val trailerUrl: String = ""
)