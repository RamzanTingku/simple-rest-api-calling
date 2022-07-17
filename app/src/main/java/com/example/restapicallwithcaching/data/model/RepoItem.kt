package com.example.restapicallwithcaching.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restapicallwithcaching.data.model.RepoOwner

@Entity(tableName = "repoItems")
data class RepoItem(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val description: String?,
    var updatedAtTimeStamp: Long?,
    val updated_at: String?,
    val stargazers_count: Int?,
    val owner: RepoOwner?
)