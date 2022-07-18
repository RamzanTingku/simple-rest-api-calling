package com.example.restapicallwithcaching.data.remote

import com.example.restapicallwithcaching.MainApplication
import com.example.restapicallwithcaching.R
import com.example.restapicallwithcaching.utils.NetworkConnectionChecker.isNetworkAvailable
import com.example.restapicallwithcaching.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository<T>() {

    private val context = MainApplication.instance
    private val dispatcher = Dispatchers.IO

    protected abstract suspend fun fetchFromDB(query: String, sort: String): List<T>

    protected abstract suspend fun fetchFromApi(query: String, limit: Int): List<T>

    protected abstract suspend fun saveFetchResult(items: List<T>)

    fun result(query: String, sort: String, limit: Int): Flow<Resource<List<T>>> = flow {
        emit(Resource.loading())
        val cache = fetchFromDB(query, sort)
        if (cache.isNotEmpty()) {
            // ****** STEP 1: VIEW CACHE ******
            emit(Resource.success(cache))
            try {
                // ****** STEP 2: MAKE NETWORK CALL, SAVE RESULT TO CACHE ******
                refresh(query, limit)
                // ****** STEP 3: VIEW CACHE ******
                emit(Resource.success(fetchFromDB(query, sort)))
            }catch (t: Throwable) {
                print(t.message)
            }
        } else {
            if (isNetworkAvailable(context)) {
                try {
                    // ****** STEP 1: MAKE NETWORK CALL, SAVE RESULT TO CACHE ******
                    refresh(query, limit)
                    // ****** STEP 2: VIEW CACHE ******
                    emit(Resource.success(fetchFromDB(query, sort)))
                } catch (t: Throwable) {
                    emit(Resource.error(context.getString(R.string.failed_loading_msg)))
                }
            } else {
                emit(Resource.error(context.getString(R.string.failed_network_msg)))
            }
        }
    }.flowOn(dispatcher)

    private suspend fun refresh(query: String, limit: Int) {
        saveFetchResult(fetchFromApi(query, limit))
    }
}