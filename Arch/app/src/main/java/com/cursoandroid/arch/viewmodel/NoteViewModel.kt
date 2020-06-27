package com.cursoandroid.arch.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cursoandroid.arch.db.Note
import com.cursoandroid.arch.repository.NoteRepository
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class NoteViewModel : ViewModel() {

    private var repository: NoteRepository = NoteRepository()
    private var allNotes: LiveData<List<Note>> = repository.getAllNotes()

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }
}