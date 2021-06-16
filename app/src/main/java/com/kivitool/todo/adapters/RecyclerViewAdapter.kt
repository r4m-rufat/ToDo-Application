package com.kivitool.todo.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kivitool.todo.EditActivity
import com.kivitool.todo.R
import com.kivitool.todo.database.ToDo
import com.kivitool.todo.database.TodoDatabase

class RecyclerViewAdapter(private val context: Context, private val list: MutableList<ToDo>?, /*val listener: RowCallBackListener*/): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.layout_for_todo_recycler_view, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {

        holder.txtNote.text = list?.get(position)!!.note

        holder.btnDelete.setOnClickListener {
            val userDao = TodoDatabase.getToDoDatabase(context).getDao()
            userDao?.deleteNotes(userDao.getAllNoteInfo()[position])
            /** last 3 lines is for async delete**/
            list.clear()
            list.addAll(userDao!!.getAllNoteInfo())
            notifyDataSetChanged()

              // these lines for interface but it doesn't work asynclly
              //listener.OnDeleteItemListener(list[position])
              //notifyDataSetChanged()
        }

        holder.btnEdit.setOnClickListener {

            var intent = Intent(context, EditActivity::class.java)
            intent.putExtra("position", position)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        var txtNote = view.findViewById(R.id.txtNote) as TextView
        var btnDelete = view.findViewById(R.id.ic_delete) as ImageView
        var btnEdit = view.findViewById(R.id.ic_edit) as ImageView

    }

//    interface RowCallBackListener{
//        fun OnDeleteItemListener(item: ToDo);
//    }

}