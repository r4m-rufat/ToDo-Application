package com.kivitool.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase(){

    abstract fun getDao(): Dao?

    companion object{

        private var INSTANCE: TodoDatabase? = null

        fun getToDoDatabase(context: Context): TodoDatabase{

            if (INSTANCE == null){

                INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "ToDoDB"
                ).allowMainThreadQueries().build()

            }

            return INSTANCE as TodoDatabase

        }
    }

}