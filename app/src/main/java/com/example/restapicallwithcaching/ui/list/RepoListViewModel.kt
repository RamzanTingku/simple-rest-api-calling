package com.example.restapicallwithcaching.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.restapicallwithcaching.data.model.RepoItem
import com.example.restapicallwithcaching.data.repository.CharacterRepository
import com.example.restapicallwithcaching.utils.Resource

class RepoListViewModel: ViewModel() {
    private val repository = CharacterRepository()

    private val _sort = MutableLiveData<String>()

    private val _repos = _sort.switchMap { _sort ->
        repository.getRepos("Android", _sort, 20)
    }
    val repos: LiveData<Resource<List<RepoItem>>> = _repos

    fun getRepos(sort: String) {
        _sort.value = sort
    }

}
