package com.nuxflix.app

import android.graphics.Color

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val year: String,
    val duration: String,
    val rating: String,
    val videoUrls: Map<String, String> = emptyMap(), // مفتاح: "360p", "480p", "720p", "1080p"
    val videoUrlBackup: String = "",
    val posterUrl: String,
    val posterColor: Int = Color.parseColor("#1A1A2E"),
    val category: String = "أفلام جديدة",
    val isFeatured: Boolean = false,
    val trailerUrl: String = ""
) {
    // دالة مساعدة لجلب الرابط حسب الجودة
    fun getVideoUrl(quality: String = "720p"): String {
        return videoUrls[quality] ?: videoUrls.values.firstOrNull() ?: videoUrlBackup.ifBlank { "" }
    }
}
