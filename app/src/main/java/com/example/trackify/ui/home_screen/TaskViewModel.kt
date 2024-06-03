package com.example.trackify.ui.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackify.data.repositories.TaskRepository
import com.example.trackify.domain.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {


    //mutableStateOf manages the state of Task object reactively
    //when task value will be updated, compose will automatically update the UI elements
    //depending on this state.
    //task is now a state variable
    var task: Task by mutableStateOf(
        Task(
            id = 0,
            title = "",
            isCompleted = false,
            startTime = LocalTime.now(),
            endTime = LocalTime.now(),
            reminder = false,
            category = "",
            priority = 0
        )
    )
        private set


    var tasks = repository.getAllTasks()
    //var taskList =repository.getAllTasks()

    var taskList: StateFlow<List<Task>> = repository.getAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    private val deletedTask: Task? = null

    //inserts a new task into the database via the repository
    //#How data gets inserted?
    //1. it just calls insert fun of repository and passes task object as argument
    fun insertTask(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.update(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    fun updateTitle(title: String) {
        task = task.copy(title = title)
    }

    fun updateStartTime(time: LocalTime) {
        task = task.copy(startTime = time)
    }

    fun updateEndTime(time: LocalTime) {
        task = task.copy(endTime = time)
    }

    fun updateIsCompleted(isCompleted: Boolean) {
        task = task.copy(isCompleted = isCompleted)
    }

    fun updateReminder(reminder: Boolean) {
        task = task.copy(reminder = reminder)
    }

    fun updateCategory(category: String) {
        task = task.copy(category = category)
    }

    fun updatePriority(priority:Int){
        task=task.copy(priority=priority)
    }

    fun getTaskById(id: Int) {
        viewModelScope.launch {
            task = repository.getTaskById(id)
        }
    }

    fun getAllTasks() {
        viewModelScope.launch {
            repository.getAllTasks()
        }
    }


    fun undoDeletedTask() {
        deletedTask?.let {
            viewModelScope.launch {
                repository.insert(it)
            }
        }
    }

}