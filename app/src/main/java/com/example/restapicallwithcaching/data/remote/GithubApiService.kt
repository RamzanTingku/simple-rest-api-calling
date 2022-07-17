package com.example.restapicallwithcaching.data.remote

import com.example.restapicallwithcaching.data.model.GithubRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {
    @GET("search/repositories")
    suspend fun getReposWithSort(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("per_page") limit: Int
    ): Response<GithubRepoResponse>


    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") query: String,
        @Query("per_page") limit: Int
    ): Response<GithubRepoResponse>
}