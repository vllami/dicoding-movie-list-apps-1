package com.dicoding.capstone.core.domain.repository

import com.dicoding.capstone.core.domain.model.Movee
import com.dicoding.capstone.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface IMoveeRepository {

    fun getList(): Flow<Resource<List<Movee>>>

    fun getWatchlist(): Flow<List<Movee>>

    fun setWatchlist(movee: Movee, state: Boolean)

}