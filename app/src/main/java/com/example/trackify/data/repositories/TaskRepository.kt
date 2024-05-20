package com.example.trackify.data.repositories

import com.example.trackify.data.local.TaskDao
import com.example.trackify.domain.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {
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