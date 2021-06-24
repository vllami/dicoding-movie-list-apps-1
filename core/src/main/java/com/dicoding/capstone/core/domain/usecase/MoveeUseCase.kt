package com.dicoding.capstone.core.domain.usecase

import com.dicoding.capstone.core.domain.model.Movee
import com.dicoding.capstone.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface MoveeUseCase {

    fun getList(): Flow<Resource<List<Movee>>>

    fun getWatchlist(): Flow<List<Movee>>

    fun setWatchlist(movee: Movee, state: Boolean)

}