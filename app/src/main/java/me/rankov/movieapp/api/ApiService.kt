package me.rankov.movieapp.api

import me.rankov.movieapp.data.MoviesResponse
import me.rankov.movieapp.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Const.NOW_PLAYING)
    suspend fun getNowPlaying(@Query("api_key") apiKey: String = Const.API_KEY): Response<MoviesResponse>

    @GET(Const.POPULAR)
    suspend fun getPopular(@Query("api_key") apiKey: String = Const.API_KEY): Response<MoviesResponse>
}