package com.dicoding.capstone.core.source.remote.network

import com.dicoding.capstone.core.source.remote.response.ListResponse
import com.dicoding.core.BuildConfig.TMDB_API_KEY
import retrofit2.http.GET

interface TMDBService {

    @GET("movie/now_playing?api_key=$TMDB_API_KEY")
    suspend fun getMovieNowPlaying(): ListResponse

}