package com.kivitool.todo.database

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Query("SELECT * FROM todo_database ORDER BY id ASC")
    fun getAllNoteInfo(): MutableList<ToDo>

    @Insert
    fun inserNotes(toDo: ToDo?)

    @Update
    fun updateNotes(toDo: ToDo?)

    @Delete
    fun deleteNotes(toDo: ToDo?)

}