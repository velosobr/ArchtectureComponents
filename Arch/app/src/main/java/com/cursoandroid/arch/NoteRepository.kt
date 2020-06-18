package com.cursoandroid.arch

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class NoteRepository(
    application: Application
) {
    private var allNotes: LiveData<List<Note>>
    private var noteDao: NoteDao?


    init {
        val db = NoteDatabase.getInstance(
            application.applicationContext
        )!!
        noteDao = db.noteDao
        allNotes = noteDao!!.getAllNotes()
    }

    fun insert(note: Note) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(
            noteDao
        ).execute()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    private class InsertNoteAsyncTask(noteDao: NoteDao?) : AsyncTask<Note, Unit, Unit>() {
        val noteDao = noteDao
        override fun doInBackground(vararg params: Note?) {
            noteDao?.insert(params[0]!!)
        }
    }

    private class DeleteAllNotesAsyncTask(val noteDao: NoteDao?) : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg params: Unit?) {
            noteDao?.deleteAllNotes()
        }
    }
}

