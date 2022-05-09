package com.example.kotlin_reminder_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin_reminder_app.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        @Volatile
        private var instances: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            if (instances == null) {
                instances = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java, "NoteDatabase"
                ).build()
            }
            return instances!!
        }
    }
}