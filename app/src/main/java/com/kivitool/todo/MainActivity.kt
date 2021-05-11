package com.kivitool.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kivitool.todo.adapters.RecyclerViewAdapter
import com.kivitool.todo.database.ToDo
import com.kivitool.todo.database.TodoDatabase

class MainActivity : AppCompatActivity() {

    lateinit var items: List<ToDo>
    lateinit var recyclerView: RecyclerView
    lateinit var editText: EditText
    lateinit var button: Button
    lateinit var refresh: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = ArrayList()

        editText = findViewById(R.id.etxtNotes)
        button = findViewById(R.id.addButton)
        refresh = findViewById(R.id.refreshButton)

        recyclerView = findViewById(R.id.todoRecyclerView)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        val userDao = TodoDatabase.getToDoDatabase(this@MainActivity).getDao()

        button.setOnClickListener {
            val notesDao = ToDo()
            notesDao.note = editText.text.toString()
            userDao?.inserNotes(notesDao)
            recyclerView.adapter = RecyclerViewAdapter(this@MainActivity, userDao?.getAllNoteInfo())
            editText.setText("")
        }

        var adapter = RecyclerViewAdapter(this@MainActivity, userDao?.getAllNoteInfo())
        adapter.notifyDataSetChanged()
        recyclerView.adapter = adapter

        refresh.setOnClickListener {

            recyclerView.adapter = RecyclerViewAdapter(this@MainActivity, userDao?.getAllNoteInfo())

        }

    }

}