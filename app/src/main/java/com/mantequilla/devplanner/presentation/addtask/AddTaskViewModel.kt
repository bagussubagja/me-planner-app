package com.mantequilla.devplanner.presentation.addtask

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantequilla.devplanner.data.params.TaskParams
import com.mantequilla.devplanner.domain.usecase.AddTaskUseCase
import com.mantequilla.devplanner.utils.PreferencesManager
import com.mantequilla.devplanner.utils.StorageKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    private val _state = MutableStateFlow<AddTaskState<ResponseBody>>(AddTaskState.loading())
    val state: StateFlow<AddTaskState<ResponseBody>> get() = _state
    private val _userId = MutableStateFlow(preferencesManager.getIdUserInfo(StorageKey.userId, ""))
    val userId  get() = _userId
    fun addTask(taskParams: TaskParams) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    addTaskUseCase(taskParams).collect() { result ->
                        when(result) {
                            is AddTaskState.ErrorPostData -> {
                                _state.value = AddTaskState.failed(Exception("Error POST Task"))
                            }
                            is AddTaskState.Loading -> {}
                            is AddTaskState.SuccessPostData -> {
                                _state.value = AddTaskState.success(201)
                            }
                        }
                    }
                } catch (e: Exception) {
                    AddTaskState.ErrorPostData(Exception("Error POST Task"))
                }
            }
        }
    }
}