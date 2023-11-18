package com.mantequilla.devplanner.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantequilla.devplanner.domain.item.TaskItem
import com.mantequilla.devplanner.domain.usecase.CalendarUseCase
import com.mantequilla.devplanner.presentation.home.homescreen.HomeScreenState
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
class CalendarViewModel @Inject constructor(
    private val calendarUseCase: CalendarUseCase,
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    private val _state =
        MutableStateFlow<CalendarState<List<TaskItem>>>(CalendarState.loading())
    val state: StateFlow<CalendarState<List<TaskItem>>> get() = _state
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getData()
    }

    fun refreshData(date: String) {
        getTasksData(
            "*",
            "eq.${preferencesManager.getIdUserInfo(StorageKey.userId, "")}",
            "eq.$date",
            "time.asc"
        )
    }

    fun getData() {
        getTasksData(
            "*",
            "eq.${preferencesManager.getIdUserInfo(StorageKey.userId, "")}",
            "eq.${Converter.getCurrentDate()}",
            "time.asc"
        )
    }

    private fun getTasksData(select: String, userId: String, date: String, order: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    _isLoading.value = true
                    calendarUseCase(select, userId, date, order).collect() { result ->
                        when (result) {
                            is CalendarState.Loading -> {}
                            is CalendarState.SuccessFetchData -> {
                                _state.value = CalendarState.success(result.data)
                            }

                            is CalendarState.ErrorFetchData -> {
                                _state.value = CalendarState.failed(result.exception)
                            }
                        }
                    }
                } catch (e: Exception) {
                    _state.value = CalendarState.failed(Exception("Error GET Task Calendar"))
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }
}