package com.example.kotlin_reminder_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "note_table")
class Note(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0;
}