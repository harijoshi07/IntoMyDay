package com.example.trackify.data.repositories

import com.example.trackify.data.local.TaskDao
import com.example.trackify.domain.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    //#How data gets inserted?
    //acts as a mediator between the DAO and the ViewModel
    //2. it forwards the responsibility to insert data to the DAO
    suspend fun insert(task: Task) {
        dao.insertTask(task)
    }

    suspend fun update(task: Task) {
        dao.updateTask(task)
    }

    suspend fun delete(task: Task) {
        dao.deleteTask(task)
    }

    fun getTaskById(id: Int): Flow<Task> {
        return dao.getTaskById(id)
    }

    fun getAllTasks(): Flow<List<Task>> {
        return dao.getAllTasks()
    }
}