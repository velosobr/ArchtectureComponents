package com.cursoandroid.arch.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * from note_table WHERE id = :key")
    fun get(key: Long): Note?

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<Note>>
//    @Query("SELECT * FROM note_table ORDER BY priority DESC")
//    fun getAllNotes(): LiveData<List<Note>>
}