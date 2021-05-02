package me.rankov.movieapp.repository

import me.rankov.movieapp.api.ApiService
import javax.inject.Inject

class MoviesRepository
@Inject
constructor(private val apiService: ApiService) {
    suspend fun getNowPlaying() = apiService.getNowPlaying()
    suspend fun getPopular() = apiService.getPopular()
}