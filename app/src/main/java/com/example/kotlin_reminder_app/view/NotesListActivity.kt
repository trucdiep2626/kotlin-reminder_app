package com.example.kotlin_reminder_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.kotlin_reminder_app.R
import com.example.kotlin_reminder_app.databinding.ActivityNotesListBinding
import com.example.kotlin_reminder_app.model.Note
import com.example.kotlin_reminder_app.viewModel.NoteViewModel
import java.io.Serializable

class NotesListActivity : AppCompatActivity() {
    val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }
    lateinit var binding: ActivityNotesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNotesList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.lifecycleOwner = this
        binding.noteViewModel = noteViewModel

        val adapter = NoteAdapter(notes = mutableListOf(), onClick = onItemClick)
        binding.rvNotesList.adapter = adapter

        binding.btnAdd.setOnClickListener(onClickAddNote)

        noteViewModel.getAllNote().observe(this, {
            it.let {
                adapter.updateNotesList(it)
            }
        })
    }


    val onClickAddNote: (View) -> Unit = {
        val intent = Intent(this, AddEditNoteActivity::class.java)
        intent.putExtra("type", "add")
        startActivity(intent)
    }

    val onItemClick: (Note) -> Unit = {
        val intent = Intent(this, AddEditNoteActivity::class.java)
        intent.putExtra("type", "edit")
        intent.putExtra("data", it)
        startActivity(intent)
    }
}