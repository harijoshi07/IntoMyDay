package com.example.trackify.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.trackify.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    //#How data gets inserted?
    //3. the @Insert annotation indicates to Room that
    // this function is intended to insert a Task object into the database.
    //The actual implementation of the insertion operation is provided by Room behind the scenes.
    @Insert
    suspend fun insertTask(task: Task) {

    }

    @Update
    suspend fun updateTask(task: Task) {

    }

    @Delete
    suspend fun deleteTask(task: Task) {

    }

    @Query("SELECT * FROM tasks_tbl WHERE id=:id")
    //With Flow as the return type, you receive notification whenever the data in the database changes.
    // The Room keeps this Flow updated for you, which means you only need to explicitly get the data once
    fun getTaskById(id: Int): Flow<Task>

    @Query("SELECT * FROM tasks_tbl")
    fun getAllTasks(): Flow<List<Task>>


}