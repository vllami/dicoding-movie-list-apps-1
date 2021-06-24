package com.dicoding.capstone.core.source.local

import com.dicoding.capstone.core.source.local.entity.MoveeEntity
import com.dicoding.capstone.core.source.local.room.MoveeDAO
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val moveeDAO: MoveeDAO) {

    fun getList(): Flow<List<MoveeEntity>> = moveeDAO.getList()

    fun getWatchlist(): Flow<List<MoveeEntity>> = moveeDAO.getWatchlist()

    suspend fun insertWatchlist(list: List<MoveeEntity>) = moveeDAO.insertWatchlist(list)

    fun setWatchlist(moveeEntity: MoveeEntity, newState: Boolean) {
        moveeEntity.watchlist = newState
        moveeDAO.updateWatchlist(moveeEntity)
    }

}