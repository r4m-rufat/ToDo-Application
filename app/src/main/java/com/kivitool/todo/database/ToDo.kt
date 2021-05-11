package com.kivitool.todo.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_database")
class ToDo (
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        var done: Boolean? =  null,
        var note: String? = null,
        var date: String? = null)
