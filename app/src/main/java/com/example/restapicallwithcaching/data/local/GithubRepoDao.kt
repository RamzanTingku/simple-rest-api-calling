package com.example.restapicallwithcaching.data.local
import com.example.restapicallwithcaching.data.model.RepoItem
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface GithubRepoDao {
    @Query("SELECT * FROM repoItems")
    fun getAllRepos() : LiveData<List<RepoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRepo(repos: List<RepoItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repoItem: RepoItem)
}