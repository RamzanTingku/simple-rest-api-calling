package com.example.restapicallwithcaching.data.remote

import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterService: CharacterService,
) : BaseDataSource() {
    suspend fun getRepos(query: String, sort: String, limit: Int) =
        getResult { characterService.getRepos(query, sort, limit) }
}