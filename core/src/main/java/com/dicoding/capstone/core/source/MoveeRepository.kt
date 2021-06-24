package com.dicoding.capstone.core.source

import com.dicoding.capstone.core.domain.model.Movee
import com.dicoding.capstone.core.domain.repository.IMoveeRepository
import com.dicoding.capstone.core.source.local.LocalDataSource
import com.dicoding.capstone.core.source.remote.RemoteDataSource
import com.dicoding.capstone.core.source.remote.network.APIResponse
import com.dicoding.capstone.core.source.remote.response.Response
import com.dicoding.capstone.core.utils.AppExecutors
import com.dicoding.capstone.core.utils.DataMapper.mapDomainToEntity
import com.dicoding.capstone.core.utils.DataMapper.mapEntitiesToDomain
import com.dicoding.capstone.core.utils.DataMapper.mapResponseToEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoveeRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors) : IMoveeRepository {

    override fun getList(): Flow<Resource<List<Movee>>> {
        return object : NetworkBoundResource<List<Movee>, List<Response>>() {
            override fun loadFromDB(): Flow<List<Movee>> {
                return localDataSource.getList().map {
                    mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movee>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<APIResponse<List<Response>>> = remoteDataSource.getList()

            override suspend fun saveCallResult(data: List<Response>) {
                mapResponseToEntities(data).also {
                    localDataSource.insertWatchlist(it)
                }
            }
        }.asFlow()
    }

    override fun getWatchlist(): Flow<List<Movee>> {
        return localDataSource.getWatchlist().map { mapEntitiesToDomain(it) }
    }

    override fun setWatchlist(movee: Movee, state: Boolean) {
        mapDomainToEntity(movee).also {
            appExecutors.diskIO().execute {
                localDataSource.setWatchlist(it, state)
            }
        }
    }

}