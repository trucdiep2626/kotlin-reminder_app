package com.example.kotlin_reminder_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_reminder_app.R
import com.example.kotlin_reminder_app.databinding.ActivityAddEditNoteBinding
import com.example.kotlin_reminder_app.databinding.ActivityNotesListBinding
import com.example.kotlin_reminder_app.model.Note
import com.example.kotlin_reminder_app.viewModel.NoteViewModel

class AddEditNoteActivity : AppCompatActivity() {
    val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }
    lateinit var binding: ActivityAddEditNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.noteViewModel = noteViewModel
        val intent = intent
        val type: String? = intent.getStringExtra("type")
        var data: Note = Note(title = "", description = "")
        if (type.equals("edit")) {
            binding.tvTitle.setText(R.string.edit_note)

            data = intent.getSerializableExtra("data") as Note
            binding.edtTitle.setText(data.title)
            binding.edtDesc.setText(data.description)
            binding.edtTitle.doAfterTextChanged {
                data.title = it.toString()
            }
            binding.edtDesc.doAfterTextChanged {
                data.description = it.toString()
            }
            binding.btnDelete.visibility = View.VISIBLE
            binding.btnDelete.setOnClickListener {
                noteViewModel.deleteNote(data)
                Toast.makeText(this, "Note Deleted", Toast.LENGTH_LONG).show()
                this.finish()
            }
        }


        binding.btnAdd.setOnClickListener {
            if (!(binding.edtTitle.text.isEmpty() && binding.edtDesc.text.isEmpty())) {
                if (!(binding.edtTitle.text.toString()
                        .isNullOrEmpty() && binding.edtDesc.text.toString().isNullOrEmpty())
                ) {
                    if (type.equals("edit")) {
                        noteViewModel.updateNote(data)
                        Toast.makeText(this, "Note Updated", Toast.LENGTH_LONG).show()
                    } else {
                        noteViewModel.insertNote(
                            Note(
                                binding.edtTitle.text.toString(),
                                binding.edtDesc.text.toString()
                            )
                        )
                        Toast.makeText(this, "Note Added", Toast.LENGTH_LONG).show()
                    }
                    this.finish()
                }
            }
        }

        binding.btnClose.setOnClickListener {
            this.finish()
        }


    }
}