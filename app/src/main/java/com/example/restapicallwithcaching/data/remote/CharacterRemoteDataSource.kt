package com.example.restapicallwithcaching.data.remote


class CharacterRemoteDataSource : BaseDataSource() {
    private val characterService = ApiClient.retrofit().create(GithubApiService::class.java)
    suspend fun getRepos(query: String, sort: String, limit: Int) =
        getResult { characterService.getRepos(query, sort, limit) }
}