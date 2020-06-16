package com.cursoandroid.arch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    private var id: Int,

    @ColumnInfo(name = "title")
    private var title: String?,
    @ColumnInfo(name = "description")
    private var description: String?,
    @ColumnInfo(name = "priority")
    private var priority: Int?
)