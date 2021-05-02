package me.rankov.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.rankov.movieapp.data.Movie
import me.rankov.movieapp.repository.MoviesRepository
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel
@Inject
constructor(private val repository: MoviesRepository) : ViewModel() {
    private val _responseNowPlaying = MutableLiveData<List<Movie>>()
    val responseNowPlayingMovies: LiveData<List<Movie>>
        get() = _responseNowPlaying

    private val _responsePopular = MutableLiveData<List<Movie>>()
    val responsePopularMovies: LiveData<List<Movie>>
        get() = _responsePopular

    init {
        getAllData()
    }

    private fun getAllData() = viewModelScope.launch {
        repository.getNowPlaying().let { response ->
            if (response.isSuccessful) {
                _responseNowPlaying.postValue(response.body()?.results)
            } else {
                Log.d("tag", "Error: ${response.raw()}")
            }
        }

        repository.getPopular().let { response ->
            if (response.isSuccessful) {
                _responsePopular.postValue(response.body()?.results)
            } else {
                Log.d("tag", "Error: ${response.raw()}")
            }
        }
    }
}