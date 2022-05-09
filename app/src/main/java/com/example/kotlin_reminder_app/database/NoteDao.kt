package com.example.kotlin_reminder_app.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlin_reminder_app.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("select * from note_table")
    fun getAllNote(): LiveData<List<Note>>
}