package com.example.restapicallwithcaching.data.repository

import com.example.restapicallwithcaching.MainApplication
import com.example.restapicallwithcaching.data.local.AppDatabase
import com.example.restapicallwithcaching.data.model.GithubRepoResponse
import com.example.restapicallwithcaching.data.model.RepoItem
import com.example.restapicallwithcaching.data.remote.ApiClient
import com.example.restapicallwithcaching.data.remote.BaseRepository
import com.example.restapicallwithcaching.data.remote.GithubApiService
import com.example.restapicallwithcaching.data.shared_pref.AppSharedPref
import com.example.restapicallwithcaching.utils.DateTimeFormatter
import com.example.restapicallwithcaching.utils.const.SharedPrefConst

class RepoRepository: BaseRepository<RepoItem>() {

    private val context = MainApplication.instance
    private val dao = AppDatabase.getDatabase(context).githubRepoDao()
    private val api = ApiClient.retrofit().create(GithubApiService::class.java)

    override suspend fun fetchFromDB(query: String, sort: String): List<RepoItem> {
        return if(sort == SharedPrefConst.SORT_STARS){
            dao.getAllReposByStars()
        }else dao.getAllReposByUpdates()
    }

    override suspend fun fetchFromApi(query: String, limit: Int): List<RepoItem> {
        return  api.getRepos(query, limit).body()?.items?.insertUpdatedTimeStamp() ?: emptyList()
    }

    override suspend fun saveFetchResult(items: List<RepoItem>) {
        dao.deleteAllItems()
        dao.insertAllRepo(items)
    }

    private fun List<RepoItem>.insertUpdatedTimeStamp(): List<RepoItem> {
        this.map {
            it.updatedAtTimeStamp = DateTimeFormatter.getFormattedDateInTimeStamp(it.updated_at ?: "", "yyyy-MM-dd'T'HH:mm:ss")
        }
        return this
    }
}