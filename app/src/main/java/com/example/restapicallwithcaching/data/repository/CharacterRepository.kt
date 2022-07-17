package com.example.restapicallwithcaching.data.repository

import com.example.restapicallwithcaching.data.local.GithubLocalDataSource
import com.example.restapicallwithcaching.data.remote.GithubRemoteDataSource
import com.example.restapicallwithcaching.data.shared_pref.AppSharedPref
import com.example.restapicallwithcaching.utils.const.SharedPrefConst
import com.example.restapicallwithcaching.utils.performGetOperation

class CharacterRepository  {
    private val remoteDataSource = GithubRemoteDataSource()
    private val localDataSource = GithubLocalDataSource()

    fun getRepos(query: String, sort: String, limit: Int) = performGetOperation(
        databaseQuery = {
            if(sort == SharedPrefConst.SORT_STARS){
                localDataSource.getAllReposByStars()
            }else{
                localDataSource.getAllReposByUpdates()
            }
        },
        networkCall = { remoteDataSource.getRepos(query, limit) },
        saveCallResult = {
            localDataSource.deleteAllItems()
            localDataSource.insertAllRepo(it.items)
        }
    )
}