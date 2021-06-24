package com.dicoding.capstone.core.source.remote

import android.util.Log
import com.dicoding.capstone.core.Constant.TAG
import com.dicoding.capstone.core.source.remote.network.APIResponse
import com.dicoding.capstone.core.source.remote.network.APIResponse.*
import com.dicoding.capstone.core.source.remote.network.TMDBService
import com.dicoding.capstone.core.source.remote.response.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val tmdbService: TMDBService) {

    suspend fun getList(): Flow<APIResponse<List<Response>>> {
        return flow {
            try {
                tmdbService.getMovieNowPlaying().also {
                    it.results.apply {
                        if (isNotEmpty()) {
                            emit(Success(this))
                        } else {
                            emit(Empty)
                        }
                    }
                }
            } catch (e: Exception) {
                e.apply {
                    emit(Error(toString()))
                    Log.e(TAG, toString())
                }
            }
        }.flowOn(Dispatchers.IO)
    }

}