package com.example.restapicallwithcaching.data.local
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.restapicallwithcaching.data.model.RepoItem


@Dao
interface GithubRepoDao {
    @Query("SELECT * FROM repoItems")
    suspend fun getAllRepos() : List<RepoItem>

    @Query("SELECT * FROM repoItems ORDER BY stargazers_count DESC")
    suspend fun getAllReposByStars() : List<RepoItem>

    @Query("SELECT * FROM repoItems ORDER BY updatedAtTimeStamp DESC")
    suspend fun getAllReposByUpdates() : List<RepoItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRepo(repos: List<RepoItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repoItem: RepoItem)

    @Query("DELETE FROM repoItems")
    fun deleteAllItems()
}