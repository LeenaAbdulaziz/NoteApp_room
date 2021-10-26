package com.example.noteapp_room.data2

import androidx.room.*
import com.example.noteapp_room.data2.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes ORDER BY note DESC")
    fun getAllUserInfo(): List<Note>

    @Insert
    fun insertnote(n: Note)
    @Update
    fun updateNote(note: Note)
    @Delete
    fun deleteNote(note: Note)

}