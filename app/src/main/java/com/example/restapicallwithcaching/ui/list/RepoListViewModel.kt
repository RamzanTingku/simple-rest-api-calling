package com.example.restapicallwithcaching.ui.list

import androidx.lifecycle.*
import com.example.restapicallwithcaching.data.model.RepoItem
import com.example.restapicallwithcaching.data.repository.RepoRepository
import com.example.restapicallwithcaching.data.shared_pref.AppSharedPref
import com.example.restapicallwithcaching.utils.Resource
import com.example.restapicallwithcaching.utils.const.SharedPrefConst
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RepoListViewModel: ViewModel() {
    private val query: String = "Android"
    private val limit: Int = 50
    private val repository = RepoRepository()
    private val _stateFlow = MutableStateFlow<Resource<List<RepoItem>>>(Resource.loading())
    val stateFlow: StateFlow<Resource<List<RepoItem>>>
        get() = _stateFlow

    init {
        refresh()
    }

    fun refresh() {
        val sort = AppSharedPref.getStringData(SharedPrefConst.CURRENT_SORT) ?: SharedPrefConst.SORT_STARS
        viewModelScope.launch {
            repository.result(query, sort, limit).collect {
                _stateFlow.tryEmit(it)
            }
        }
    }
}
