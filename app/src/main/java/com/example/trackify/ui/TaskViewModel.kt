package com.example.trackify.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackify.data.repositories.TaskRepository
import com.example.trackify.domain.model.Task
import com.example.trackify.ui.add_edit_screen.AddEditScreenEvent
import com.example.trackify.ui.home_screen.HomeScreenEvent
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

    //Home Screen Events
    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnCompleted ->{
                viewModelScope.launch {
                    task=repository.getTaskById(event.taskId)
                    task = task.copy(isCompleted = event.isCompleted)
                    repository.update(task)
                }
            }
        }
    }

    //Add Edit Screen Events
    fun onEvent(event: AddEditScreenEvent){
        when(event){
            is AddEditScreenEvent.OnAddTaskClick->{
                viewModelScope.launch {
                    repository.insert(event.task)
                }
            }

            is AddEditScreenEvent.OnDeleteTaskClick -> {
                viewModelScope.launch {
                    repository.delete(task)
                }
            }
            is AddEditScreenEvent.OnUpdateTitle -> {
                viewModelScope.launch {
                    task=task.copy(title = event.title)
                }
            }
            is AddEditScreenEvent.OnUpdateStartTime -> {
                viewModelScope.launch {
                    task=task.copy(startTime = event.startTime)
                }
            }
            is AddEditScreenEvent.OnUpdateEndTime -> {
                viewModelScope.launch {
                    task=task.copy(endTime = event.endTime)
                }
            }
            is AddEditScreenEvent.OnUpdateReminder -> {
                viewModelScope.launch {
                    task=task.copy(reminder = event.reminder)
                }
            }
            is AddEditScreenEvent.OnUpdatePriority -> {
                viewModelScope.launch {
                    task=task.copy(priority = event.priority.ordinal)
                }
            }
            is AddEditScreenEvent.OnUpdateTask ->{
                viewModelScope.launch {
                    repository.update(task)
                }
            }
        }
    }


    //inserts a new task into the database via the repository
    //#How data gets inserted?
    //1. it just calls insert fun of repository and passes task object as argument
    fun insertTask(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
        }
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
}