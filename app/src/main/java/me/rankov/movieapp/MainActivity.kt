package me.rankov.movieapp

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.rankov.movieapp.adapter.MovieAdapter
import me.rankov.movieapp.adapter.NowPlayingAdapter
import me.rankov.movieapp.databinding.ActivityMainBinding
import me.rankov.movieapp.viewmodel.MoviesViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var nowPlayingAdapter: MovieAdapter
    private lateinit var popularMovieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()
        setupMovieViews()
    }

    private fun setupActionBar() {
        supportActionBar?.title = "TMDB"
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.orange
                )
            )
        )
    }

    private fun setupMovieViews() {
        nowPlayingAdapter = NowPlayingAdapter()
        binding.nowPlaying.apply {
            adapter = nowPlayingAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }
        viewModel.responseNowPlayingMovies.observe(this, { moviesList ->
            nowPlayingAdapter.movies = moviesList
        })

        popularMovieAdapter = MovieAdapter()
        binding.popular.apply {
            adapter = popularMovieAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }
        viewModel.responsePopularMovies.observe(this, { moviesList ->
            popularMovieAdapter.movies = moviesList
        })
    }
}