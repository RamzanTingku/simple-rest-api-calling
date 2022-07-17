package com.example.restapicallwithcaching.data.local
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.restapicallwithcaching.data.model.RepoItem


@Dao
interface GithubRepoDao {
    @Query("SELECT * FROM repoItems")
    fun getAllRepos() : LiveData<List<RepoItem>>

    @Query("SELECT * FROM repoItems ORDER BY stargazers_count DESC")
    fun getAllReposByStars() : LiveData<List<RepoItem>>

    @Query("SELECT * FROM repoItems ORDER BY updatedAtTimeStamp DESC")
    fun getAllReposByUpdates() : LiveData<List<RepoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRepo(repos: List<RepoItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repoItem: RepoItem)

    @Query("DELETE FROM repoItems")
    fun deleteAllItems()
}