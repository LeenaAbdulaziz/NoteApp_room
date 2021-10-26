package com.example.noteapp_room

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp_room.data2.Note
import com.example.noteapp_room.data2.NoteDatabase

class MainActivity : AppCompatActivity() {
    lateinit var save: Button
    lateinit var note: EditText
    lateinit var recycle: RecyclerView
    lateinit var list:List<Note>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        note=findViewById(R.id.ednote)
        save=findViewById(R.id.btnsave)
        recycle=findViewById(R.id.rv)
      list= listOf()
        val ob= NoteDatabase.getinstant(applicationContext)
        save.setOnClickListener {
            val s1=note.text.toString()
            if(s1.isNotEmpty()) {
                ob.NoteDao().insertnote(Note(0, s1))
                note.text.clear()
                Toast.makeText(applicationContext, "data successfully added", Toast.LENGTH_SHORT)
                    .show()


                updatedrecycle()
            }
            else{
                Toast.makeText(applicationContext,"please add note first",Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun updatedrecycle(){
        val ob= NoteDatabase.getinstant(applicationContext)
        var notes=ob.NoteDao().getAllUserInfo()
        recycle.adapter = RecycleView (this, notes)
        recycle.layoutManager = LinearLayoutManager(this)


    }
    fun update(n: Note) {
        val ob= NoteDatabase.getinstant(applicationContext)
        var at= AlertDialog.Builder(this)
        at.setTitle("Edit Note")
        val input = EditText(this)
        input.setText(n.note)
        at.setView(input)
        at.setPositiveButton("Update", DialogInterface.OnClickListener { dialogInterface, i ->
             ob.NoteDao().updateNote(Note(n.id,input.text.toString()))
            updatedrecycle()
        })
        at.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        at.show()
    }

    fun confirm(note: Note){

        var at= AlertDialog.Builder(this)
        at.setTitle("delete Note")
        at.setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->
            deleteitem(note)
        })
        at.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        at.show()
    }

    private fun deleteitem(note: Note) {
        val ob= NoteDatabase.getinstant(applicationContext)
        ob.NoteDao().deleteNote(note)
        updatedrecycle()
    }
}