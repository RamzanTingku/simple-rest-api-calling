package com.example.restapicallwithcaching.data.local

import com.example.restapicallwithcaching.data.model.RepoItem
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.restapicallwithcaching.data.model.OwnerTypeConverter


@Database(entities = [RepoItem::class], version = 2, exportSchema = false)
@TypeConverters(OwnerTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun githubRepoDao(): GithubRepoDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "github-repo")
                .fallbackToDestructiveMigration()
                .build()
    }

}