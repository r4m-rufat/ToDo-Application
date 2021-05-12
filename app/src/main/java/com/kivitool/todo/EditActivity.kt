package com.kivitool.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.kivitool.todo.database.ToDo
import com.kivitool.todo.database.TodoDatabase

class EditActivity : AppCompatActivity() {

    private lateinit var editNote: EditText
    private lateinit var changeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val position = intent.getIntExtra("position", 0)

        editNote = findViewById(R.id.etxtChangeNote)
        changeButton = findViewById(R.id.changeButton)

        Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()

        changeButton.setOnClickListener {

            val dao2 = TodoDatabase.getToDoDatabase(this@EditActivity).getDao()
            val todo = ToDo(position, dao2!!.getAllNoteInfo()[position].done, editNote.text.trim().toString(), dao2!!.getAllNoteInfo()[position].date)
            dao2!!.updateNotes(todo)
            startActivity(Intent(this@EditActivity, MainActivity::class.java))

        }

    }
}