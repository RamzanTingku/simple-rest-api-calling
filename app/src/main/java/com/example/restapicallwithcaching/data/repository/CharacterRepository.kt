package com.example.restapicallwithcaching.data.repository

import com.example.restapicallwithcaching.data.remote.CharacterRemoteDataSource
import com.example.restapicallwithcaching.utils.performGetOperation
import javax.inject.Inject

class CharacterRepository  {
    private val remoteDataSource = CharacterRemoteDataSource()
    fun getRepos(query: String, sort: String, limit: Int) = performGetOperation(
        networkCall = { remoteDataSource.getRepos(query, sort, limit) }
    )
}