package com.example.kotlin_reminder_app.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.kotlin_reminder_app.model.Note
import com.example.kotlin_reminder_app.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : ViewModel() {

    private val noteRepository: NoteRepository = NoteRepository(application)

    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch { noteRepository.deleteNote(note) }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun getAllNote(): LiveData<List<Note>> = noteRepository.getAllNote()

    class NoteViewModelFactory(var application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
                return NoteViewModel(application) as T
            }
            throw  IllegalAccessException("unable construct viewmodel")
        }
    }
}