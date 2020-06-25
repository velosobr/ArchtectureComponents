package com.cursoandroid.arch.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "description")
    val description: String?
//    @ColumnInfo(name = "priority")
//    val priority: Int? = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}