package com.example.restapicallwithcaching.data.remote

import com.example.restapicallwithcaching.data.model.GithubRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("per_page") limit: Int
    ): Response<GithubRepoResponse>
}