package com.dicoding.capstone.core.source.local.room

import androidx.room.*
import com.dicoding.capstone.core.source.local.entity.MoveeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoveeDAO {

    @Query("SELECT * FROM movee")
    fun getList(): Flow<List<MoveeEntity>>

    @Query("SELECT * FROM movee WHERE watchlist = 1")
    fun getWatchlist(): Flow<List<MoveeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlist(movies: List<MoveeEntity>)

    @Update
    fun updateWatchlist(entity: MoveeEntity)

}