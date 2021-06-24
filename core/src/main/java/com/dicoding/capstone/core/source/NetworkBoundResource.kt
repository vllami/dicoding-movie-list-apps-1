package com.dicoding.capstone.core.source

import com.dicoding.capstone.core.source.remote.network.APIResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        val dbSource = loadFromDB().first()

        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())

            when(val apiResponse = createCall().first()) {
                is APIResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
                is APIResponse.Empty -> {
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
                is APIResponse.Error -> {
                    @Suppress("RemoveExplicitTypeArguments")
                    emit(Resource.Error<ResultType>(apiResponse.message))
                }
            }
        } else {
            emitAll(loadFromDB().map {
                Resource.Success(it)
            })
        }
    }

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<APIResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result

}