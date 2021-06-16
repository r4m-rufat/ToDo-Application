package com.kivitool.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kivitool.todo.adapters.RecyclerViewAdapter
import com.kivitool.todo.database.ToDo
import com.kivitool.todo.database.TodoDatabase

class MainActivity : AppCompatActivity() /*, RecyclerViewAdapter.RowCallBackListener*/ {

    lateinit var recyclerView: RecyclerView
    lateinit var editText: EditText
    lateinit var button: Button
    lateinit var refresh: ImageView
    lateinit var list: MutableList<ToDo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.etxtNotes)
        button = findViewById(R.id.addButton)
        refresh = findViewById(R.id.refreshButton)

        recyclerView = findViewById(R.id.todoRecyclerView)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        val userDao = TodoDatabase.getToDoDatabase(this@MainActivity).getDao()
        list = userDao!!.getAllNoteInfo()

        val notesDao = ToDo()

        button.setOnClickListener {
            notesDao.note = editText.text.trim().toString()
            userDao?.inserNotes(notesDao)
            list = userDao.getAllNoteInfo()
            recyclerView.adapter = RecyclerViewAdapter(this@MainActivity, list)
            editText.setText("")
        }

        var adapter = RecyclerViewAdapter(this@MainActivity, list)
        adapter.notifyDataSetChanged()
        recyclerView.adapter = adapter

        refresh.setOnClickListener {

            recyclerView.adapter = RecyclerViewAdapter(this@MainActivity, userDao?.getAllNoteInfo())

        }

        var itemTouchCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position =  viewHolder.adapterPosition
                userDao?.deleteNotes(userDao?.getAllNoteInfo()[position])
                recyclerView.adapter = RecyclerViewAdapter(this@MainActivity, userDao?.getAllNoteInfo())

            }

        }

        var itemTouchHelper = ItemTouchHelper(itemTouchCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

//    override fun OnDeleteItemListener(item: ToDo) {
//        val userDao = TodoDatabase.getToDoDatabase(this).getDao()
//        userDao!!.deleteNotes(item)
//        list = userDao!!.getAllNoteInfo()
//    }

}