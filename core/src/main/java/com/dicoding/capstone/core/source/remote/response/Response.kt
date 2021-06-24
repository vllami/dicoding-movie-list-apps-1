package com.dicoding.capstone.core.source.remote.response

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrop_path")
    val backdrop: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("overview")
    val synopsis: String
)