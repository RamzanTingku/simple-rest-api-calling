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

    protected abstract suspend fun query(): List<T>

    protected abstract suspend fun fetch(query: String, sort: String, limit: Int): List<T>

    protected abstract suspend fun saveFetchResult(items: List<T>)

    fun result(query: String, sort: String, limit: Int): Flow<Resource<List<T>>> = flow {
        emit(Resource.loading())
        val cache = query()
        if (cache.isNotEmpty()) {
            // ****** STEP 1: VIEW CACHE ******
            emit(Resource.success(cache))
            try {
                // ****** STEP 2: MAKE NETWORK CALL, SAVE RESULT TO CACHE ******
                refresh(query, sort, limit)
                // ****** STEP 3: VIEW CACHE ******
                emit(Resource.success(query()))
            }catch (t: Throwable) {
                print(t.message)
            }
        } else {
            if (isNetworkAvailable(context)) {
                try {
                    // ****** STEP 1: MAKE NETWORK CALL, SAVE RESULT TO CACHE ******
                    refresh(query, sort, limit)
                    // ****** STEP 2: VIEW CACHE ******
                    emit(Resource.success(query()))
                } catch (t: Throwable) {
                    emit(Resource.error(context.getString(R.string.failed_loading_msg)))
                }
            } else {
                emit(Resource.error(context.getString(R.string.failed_network_msg)))
            }
        }
    }.flowOn(dispatcher)

    private suspend fun refresh(query: String, sort: String, limit: Int) {
        saveFetchResult(fetch(query, sort, limit))
    }
}