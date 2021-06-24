package com.dicoding.capstone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movee(
    val id: Int,
    val backdrop: String,
    val poster: String,
    val title: String,
    val releaseDate: String,
    val rating: Double,
    val synopsis: String,
    val watchlist: Boolean
) : Parcelable