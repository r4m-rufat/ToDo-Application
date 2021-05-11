package com.kivitool.todo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kivitool.todo.R
import com.kivitool.todo.database.ToDo
import com.kivitool.todo.database.TodoDatabase

class RecyclerViewAdapter(val context: Context, val list: List<ToDo>?): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.layout_for_todo_recycler_view, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {

        holder.txtNote.text = list?.get(position)!!.note

        holder.btnDelete.setOnClickListener {
            val userDao = TodoDatabase.getToDoDatabase(context).getDao()
            userDao?.deleteNotes(userDao.getAllNoteInfo()[position])
        }

    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        var txtNote = view.findViewById(R.id.txtNote) as TextView
        var btnDelete = view.findViewById(R.id.ic_delete) as ImageView

    }



}