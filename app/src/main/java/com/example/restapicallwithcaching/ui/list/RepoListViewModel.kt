package com.example.restapicallwithcaching.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.restapicallwithcaching.data.model.RepoItem
import com.example.restapicallwithcaching.data.repository.CharacterRepository
import com.example.restapicallwithcaching.data.shared_pref.AppSharedPref
import com.example.restapicallwithcaching.utils.Resource
import com.example.restapicallwithcaching.utils.const.SharedPrefConst

class RepoListViewModel: ViewModel() {

    private val _sort = MutableLiveData<String>()
    private val repository = CharacterRepository()

    init {
        _sort.value = AppSharedPref.getStringData(SharedPrefConst.CURRENT_SORT)
    }

    private val _repos = _sort.switchMap { _sort ->
        repository.getRepos("Android", _sort, 20)
    }
    val repos: LiveData<Resource<List<RepoItem>>> = _repos

    fun getRepos(sort: String) {
        _sort.value = sort
    }
}
