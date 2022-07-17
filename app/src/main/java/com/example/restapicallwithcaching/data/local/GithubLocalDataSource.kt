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

    override fun getAllReposByStars(): LiveData<List<RepoItem>> {
        return githubRepoDao.getAllReposByStars()
    }

    override fun getAllReposByUpdates(): LiveData<List<RepoItem>> {
        return githubRepoDao.getAllReposByUpdates()
    }

    override suspend fun insertAllRepo(repos: List<RepoItem>) {
        return githubRepoDao.insertAllRepo(repos)
    }

    override suspend fun insertRepo(repoItem: RepoItem) {
        return githubRepoDao.insertRepo(repoItem)
    }

    override fun deleteAllItems() {
        return githubRepoDao.deleteAllItems()
    }
}