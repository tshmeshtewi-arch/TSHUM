
package com.nuxflix.app

import com.nuxflix.SettingsActivity
import com.nuxflix.AccountActivity
import com.nuxflix.DownloadsActivity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchInput: EditText
    private lateinit var searchIcon: ImageButton
    private lateinit var brandText: TextView
    private lateinit var emptyText: TextView
    private lateinit var chipGroup: ChipGroup
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var adapter: MovieAdapter
    private var currentCategory: String = "الكل"
    private var currentQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.home_recycler)
        searchInput = findViewById(R.id.search_input)
        searchIcon = findViewById(R.id.search_icon)
        brandText = findViewById(R.id.brand_text)
        emptyText = findViewById(R.id.empty_text)
        chipGroup = findViewById(R.id.chip_group)
        bottomNav = findViewById(R.id.bottom_nav)

        setupRecyclerView()
        setupSearch()
        setupCategoryChips()
        setupBottomNav()
        loadMovies()
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter(
            movies = emptyList()
        ) { movie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("movie_id", movie.id)
            startActivity(intent)
        }

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    private fun setupSearch() {
        // أيقونة البحث: تخفي الاسم وتظهر البحث
        searchIcon.setOnClickListener {
            brandText.visibility = View.GONE
            searchInput.visibility = View.VISIBLE
            searchInput.requestFocus()
            searchIcon.visibility = View.GONE
        }

        searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                currentQuery = searchInput.text.toString()
                loadMovies()
                true
            } else false
        }

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                currentQuery = s.toString()
                loadMovies()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // زر الرجوع في البحث (لما المستخدم يضغط Back)
        searchInput.setOnKeyListener { _, keyCode, event ->
            if (keyCode == android.view.KeyEvent.KEYCODE_BACK && event.action == android.view.KeyEvent.ACTION_UP) {
                hideSearch()
                true
            } else false
        }
    }

    private fun hideSearch() {
        searchInput.visibility = View.GONE
        searchIcon.visibility = View.VISIBLE
        brandText.visibility = View.VISIBLE
        searchInput.text.clear()
        currentQuery = ""
        loadMovies()
    }

    override fun onBackPressed() {
        if (searchInput.visibility == View.VISIBLE) {
            hideSearch()
        } else {
            super.onBackPressed()
        }
    }

    private fun setupCategoryChips() {
        val categories = listOf("الكل") + MovieRepository.getCategories()
        for (cat in categories) {
            val chip = layoutInflater.inflate(
                R.layout.item_chip,
                chipGroup,
                false
            ) as com.google.android.material.chip.Chip
            chip.text = cat
            chip.isChecked = cat == "الكل"
            chip.setOnClickListener {
                currentCategory = cat
                loadMovies()
            }
            chipGroup.addView(chip)
        }
    }

    private fun setupBottomNav() {
    bottomNav.setOnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                currentCategory = "الكل"
                hideSearch()
                chipGroup.check(chipGroup.getChildAt(0).id)
                loadMovies()
                true
            }
            R.id.nav_categories -> {
                chipGroup.visibility = if (chipGroup.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                true
            }
            R.id.nav_downloads -> {
                startActivity(Intent(this, DownloadsActivity::class.java))
                true
            }
            R.id.nav_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            R.id.nav_account -> {
                startActivity(Intent(this, AccountActivity::class.java))
                true
            }
            else -> false
        }
    }
}

    private fun loadMovies() {
        val movies = if (currentQuery.isNotBlank()) {
            MovieRepository.searchMovies(currentQuery)
        } else if (currentCategory == "الكل") {
            MovieRepository.getAllMovies()
        } else {
            MovieRepository.getMoviesByCategory(currentCategory)
        }
        adapter.updateMovies(movies)
        emptyText.visibility = if (movies.isEmpty()) View.VISIBLE else View.GONE
    }
}
