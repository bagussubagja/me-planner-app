package com.mantequilla.devplanner.presentation.home.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantequilla.devplanner.domain.item.TaskItem
import com.mantequilla.devplanner.domain.usecase.TaskUseCase
import com.mantequilla.devplanner.utils.Converter
import com.mantequilla.devplanner.utils.PreferencesManager
import com.mantequilla.devplanner.utils.StorageKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase,
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    private val _state =
        MutableStateFlow<HomeScreenState<List<TaskItem>>>(HomeScreenState.loading())
    val state: StateFlow<HomeScreenState<List<TaskItem>>> get() = _state
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getData(false)
    }

    fun logout () {
        preferencesManager.clearAllPreferences()
    }

    fun getData(isRefresh: Boolean) {
        getTasksData(
            "*",
            "eq.${preferencesManager.getIdUserInfo(StorageKey.userId, "")}",
            "eq.${Converter.getCurrentDate()}",
            "time.asc",
            isRefresh =  isRefresh,
        )
    }

    private fun getTasksData(select: String, userId: String, date: String, order: String, isRefresh: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    if (isRefresh) {
                        _isLoading.value = true
                    }
                    taskUseCase(select, userId, date, order).collect() { result ->
                        when (result) {
                            is HomeScreenState.Loading -> {}
                            is HomeScreenState.SuccessFetchData -> {
                                _state.value = HomeScreenState.success(result.data)
                            }

                            is HomeScreenState.ErrorFetchData -> {
                                _state.value = HomeScreenState.failed(result.exception)
                            }
                        }
                    }
                } catch (e: Exception) {
                    _state.value = HomeScreenState.failed(Exception("Error GET Task"))
                }
                if (isRefresh) {
                    _isLoading.value = false
                }
            }
        }
    }
}