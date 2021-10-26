package com.example.noteapp_room.data2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class],version = 1,exportSchema = false)

abstract class NoteDatabase: RoomDatabase() {

        companion object{
            var instant: NoteDatabase?=null
            fun getinstant(context: Context): NoteDatabase {
                if(instant !=null)
                {
                    return instant as NoteDatabase
                }
                instant = Room.databaseBuilder(context, NoteDatabase::class.java,"name").run{
                    allowMainThreadQueries() }.build()
                return instant as NoteDatabase
            }
        }
        abstract fun NoteDao(): NoteDao;
    }
