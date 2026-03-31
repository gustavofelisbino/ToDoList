package br.edu.satc.todolistcompose.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAll(): List<TaskData>

    @Insert
    fun insert(task: TaskData)

    @Update
    fun update(task: TaskData)

    @Query("DELETE FROM tasks WHERE id = :id")
    fun delete(id: Int)
}