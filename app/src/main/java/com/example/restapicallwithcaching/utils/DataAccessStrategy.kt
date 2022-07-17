package com.example.restapicallwithcaching.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.restapicallwithcaching.MainApplication
import com.example.restapicallwithcaching.utils.Resource.Status.*
import kotlinx.coroutines.Dispatchers
import com.example.restapicallwithcaching.utils.NetworkConnectionChecker

fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> Resource<A>,
                               saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)
        if(NetworkConnectionChecker.isNetworkAvailable(MainApplication.instance)){
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == SUCCESS) {
                saveCallResult(responseStatus.data!!)

            } else if (responseStatus.status == ERROR) {
                emit(Resource.error(responseStatus.message!!))
                emitSource(source)
            }
        }else{
            val hasDataString = " Showing Data from cache..."
            emit(Resource.error("Internet is not available.$hasDataString"))
            emitSource(source)
        }

    }