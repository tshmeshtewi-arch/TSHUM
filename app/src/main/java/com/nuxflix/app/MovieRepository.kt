package com.nuxflix.app

object MovieRepository {

    private val moviesList = listOf(
        Movie(
            id = 1,
            title = "Big Buck Bunny",
            description = "قصة أرنب كبير وطيب تتعكر حياتهم بسبب ثلاثة حيوانات مزعجة. فيلم قصير مذهل من إنتاج Blender Foundation.",
            year = "2026",
            duration = "10 دقائق",
            rating = "⭐ 8.5",
            videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
            videoUrlBackup = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            posterUrl = "https://upload.wikimedia.org/wikipedia/commons/c/c5/Big_buck_bunny_poster_big.jpg",
            category = "أفلام كرتون",
            isFeatured = true
        ),
        Movie(
            id = 2,
            title = "Elephant Dream",
            description = "أول فيلم مفتوح المصدر في العالم. قصة خيالية عن إمبراطورية من الفيلة.",
            year = "2026",
            duration = "12 دقيقة",
            rating = "⭐ 7.8",
            videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            posterUrl = "https://upload.wikimedia.org/wikipedia/commons/e/e8/Elephants_Dream_s5_both.jpg",
            category = "أفلام أنميشن",
            isFeatured = true
        ),
        Movie(
            id = 3,
            title = "Subaru Outback On Street",
            description = "مشاهد قيادة مثيرة لسيارة Subaru في شوارع المدينة.",
            year = "2026",
            duration = "15 دقيقة",
            rating = "⭐ 7.2",
            videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
            posterUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/SubaruOutbackOnStreetAndDirt.jpg",
            category = "وثائقيات",
            isFeatured = false
        ),
        Movie(
            id = 4,
            title = "Tears of Steel",
            description = "فيلم خيال علمي مميز من Blender عن معركة البشر ضد الروبوتات في المستقبل.",
            year = "2026",
            duration = "14 دقيقة",
            rating = "⭐ 8.0",
            videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
            posterUrl = "https://upload.wikimedia.org/wikipedia/commons/1/13/Tears_of_Steel_poster.jpg",
            category = "خيال علمي",
            isFeatured = false
        ),
        Movie(
            id = 5,
            title = "Sintel Trailer",
            description = "فيلم درامي من إنتاج Blender عن فتاة تبحث عن تنينها الصغير في عالم خطير.",
            year = "2026",
            duration = "15 دقيقة",
            rating = "⭐ 8.3",
            videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
            posterUrl = "https://upload.wikimedia.org/wikipedia/commons/8/8e/Sintel_poster.jpg",
            category = "دراما",
            isFeatured = false
        )
    )

    fun getAllMovies(): List<Movie> = moviesList

    fun getFeaturedMovies(): List<Movie> = moviesList.filter { it.isFeatured }

    fun getMovieById(id: Int): Movie? = moviesList.find { it.id == id }

    fun getCategories(): List<String> = moviesList.map { it.category }.distinct()

    fun getMoviesByCategory(category: String): List<Movie> = moviesList.filter { it.category == category }

    fun searchMovies(query: String): List<Movie> {
        if (query.isBlank()) return moviesList
        return moviesList.filter {
            it.title.contains(query, ignoreCase = true) ||
            it.description.contains(query, ignoreCase = true) ||
            it.category.contains(query, ignoreCase = true)
        }
    }
}