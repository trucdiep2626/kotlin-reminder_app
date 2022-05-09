package com.example.kotlin_reminder_app.repository

import androidx.lifecycle.LiveData
import com.example.kotlin_reminder_app.model.Note

interface INoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getAllNote(): LiveData<List<Note>>
}