package com.example.restapicallwithcaching.data.remote

import com.example.restapicallwithcaching.data.model.GithubRepoResponse
import com.example.restapicallwithcaching.utils.DateTimeFormatter
import com.example.restapicallwithcaching.utils.Resource
import retrofit2.Response


class GithubRemoteDataSource : BaseDataSource() {
    private val characterService = ApiClient.retrofit().create(GithubApiService::class.java)
    suspend fun getRepos(query: String, limit: Int): Resource<GithubRepoResponse> {
        return getResult {
            var result = characterService.getRepos(query, limit)
            if (result.isSuccessful && !result.body()?.items.isNullOrEmpty()) {
                result = insertUpdatedTimeStamp(result)
            }
            return@getResult result
        }
    }

    private fun insertUpdatedTimeStamp(result: Response<GithubRepoResponse>): Response<GithubRepoResponse> {
        result.body()?.items?.map {
            it.updatedAtTimeStamp = DateTimeFormatter.getFormattedDateInTimeStamp(it.updated_at ?: "", "yyyy-MM-dd'T'HH:mm:ss")
        }
        return result
    }
}