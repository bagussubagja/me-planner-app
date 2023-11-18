package com.mantequilla.devplanner.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantequilla.devplanner.data.params.TaskParams
import com.mantequilla.devplanner.domain.usecase.DeleteTaskUseCase
import com.mantequilla.devplanner.domain.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<DetailState<ResponseBody>>(DetailState.loading())
    val state: StateFlow<DetailState<ResponseBody>> get() = _state

    fun deleteTask(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    deleteTaskUseCase(id).collect() { result ->
                        when (result) {
                            is DetailState.ErrorUpdateData -> {
                                _state.value = DetailState.failed(Exception("Error Delete Task"))
                            }

                            is DetailState.Loading -> {}
                            is DetailState.SuccessUpdateData -> {
                                _state.value = DetailState.success(204)
                            }
                        }
                    }
                } catch (e: Exception) {
                    DetailState.failed(java.lang.Exception("Error Update Task!"))
                }
            }
        }
    }
    fun updateTask(taskParams: TaskParams, id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    updateTaskUseCase(taskParams, id).collect() { result ->
                        when (result) {
                            is DetailState.ErrorUpdateData -> {
                                _state.value = DetailState.failed(Exception("Error Update Task"))
                            }

                            is DetailState.Loading -> {}
                            is DetailState.SuccessUpdateData -> {
                                _state.value = DetailState.success(204)
                            }
                        }
                    }
                } catch (e: Exception) {
                    DetailState.failed(java.lang.Exception("Error Update Task!"))
                }
            }
        }
    }
}