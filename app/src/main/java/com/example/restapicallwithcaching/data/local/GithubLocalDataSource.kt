package com.example.restapicallwithcaching.data.local
import com.example.restapicallwithcaching.data.model.RepoItem
import androidx.lifecycle.LiveData
import com.example.restapicallwithcaching.MainApplication

class GithubLocalDataSource: GithubRepoDao {

    private var database = AppDatabase.getDatabase(MainApplication.instance)

    private val githubRepoDao: GithubRepoDao = database.githubRepoDao()

    override fun getAllRepos(): LiveData<List<RepoItem>> {
        return githubRepoDao.getAllRepos()
    }

    override suspend fun insertAllRepo(repos: List<RepoItem>) {
        return githubRepoDao.insertAllRepo(repos)
    }

    override suspend fun insertRepo(repoItem: RepoItem) {
        return githubRepoDao.insertRepo(repoItem)
    }
}