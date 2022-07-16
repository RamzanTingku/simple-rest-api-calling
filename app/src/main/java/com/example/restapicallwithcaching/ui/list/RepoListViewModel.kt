package com.example.restapicallwithcaching.ui.list

import androidx.lifecycle.ViewModel
import com.example.restapicallwithcaching.data.repository.CharacterRepository

class RepoListViewModel: ViewModel() {
    private val repository = CharacterRepository()
    val repos = repository.getRepos("Android", "stars", 10)
}
