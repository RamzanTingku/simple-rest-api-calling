package com.example.restapicallwithcaching.data.repository

import com.example.restapicallwithcaching.data.local.GithubLocalDataSource
import com.example.restapicallwithcaching.data.remote.GithubRemoteDataSource
import com.example.restapicallwithcaching.utils.performGetOperation

class CharacterRepository  {
    private val remoteDataSource = GithubRemoteDataSource()
    private val localDataSource = GithubLocalDataSource()

    fun getRepos(query: String, sort: String, limit: Int) = performGetOperation(
        databaseQuery = { localDataSource.getAllRepos() },
        networkCall = { remoteDataSource.getRepos(query, sort, limit) },
        saveCallResult = { localDataSource.insertAllRepo(it.items) }
    )
}