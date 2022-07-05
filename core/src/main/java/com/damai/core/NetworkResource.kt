package com.damai.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
abstract class NetworkResource<T: BaseModel>(
    private val schedulerProvider: SchedulerProvider
) {

    fun asFlow(): Flow<Resource<T>> = flow {
        // check if should fetch data from remote or not
        if (shouldFetchFromRemote()) {
            val remoteResponse = safeApiCall(dispatcher = schedulerProvider.io()) {
                remoteFetch()   // fetch the remote source provided
            }

            when (remoteResponse) {
                is ResultWrapper.Success -> {
                    if (shouldFetchRemoteAndSaveLocal()) {
                        remoteResponse.value?.let {
                            val localData = withContext(schedulerProvider.io()) {
                                saveLocal(it)
                                localFetch()
                            }
                            emit(Resource.Success(model = localData, DataSource.CACHE))
                        }
                    } else {
                        emit(Resource.Success(model = remoteResponse.value, DataSource.REMOTE))
                    }
                }
                is ResultWrapper.GenericError -> {
                    if (shouldFetchLocalOnError()) {
                        val localData = withContext(schedulerProvider.io()) {
                            localFetch()
                        }
                        emit(Resource.Success(model = localData, DataSource.CACHE))
                    } else {
                        emit(Resource.Error(errorData = ErrorData(
                            remoteResponse.code,
                            remoteResponse.message
                        )))
                    }
                }
            }
        } else {
            // get from cache
            val localData = withContext(schedulerProvider.io()) {
                localFetch()
            }
            emit(Resource.Success(model = localData, DataSource.CACHE))
        }
    }

    abstract suspend fun remoteFetch(): T
    open suspend fun saveLocal(data: T) {}
    open suspend fun localFetch(): T? = null
    open fun onFetchFailed(throwable: Throwable) = Unit
    open fun shouldFetchFromRemote() = true
    open fun shouldFetchRemoteAndSaveLocal() = false
    open fun shouldFetchLocalOnError() = false
}