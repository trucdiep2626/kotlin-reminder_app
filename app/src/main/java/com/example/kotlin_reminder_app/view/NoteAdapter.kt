package com.example.kotlin_reminder_app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_reminder_app.R
import com.example.kotlin_reminder_app.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    var notes: MutableList<Note>
    val onClick: (Note) -> Unit

    constructor(
        notes: MutableList<Note>,
        onClick: (Note) -> Unit,
    ) : super() {
        this.notes = notes
        this.onClick = onClick
    }

    fun updateNotesList(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tvTitle)
        var description: TextView = itemView.findViewById(R.id.tvDescription)
        var item: View = itemView.findViewById(R.id.noteItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.description.setText(note.description)
        holder.item.setOnClickListener { onClick(note) }
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}