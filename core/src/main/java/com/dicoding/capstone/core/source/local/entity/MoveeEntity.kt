package com.dicoding.capstone.core.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dicoding.capstone.core.Constant.DATABASE_NAME

@Entity(tableName = DATABASE_NAME)
data class MoveeEntity(
    @PrimaryKey @NonNull val id: Int,
    val backdrop: String,
    val poster: String,
    val title: String,
    val releaseDate: String,
    val rating: Double,
    val synopsis: String,
    var watchlist: Boolean = false
)