package com.cursoandroid.arch.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursoandroid.arch.db.Note
import com.cursoandroid.arch.adapter.NoteAdapter
import com.cursoandroid.arch.viewmodel.NoteViewModel
import com.cursoandroid.arch.R
import com.cursoandroid.arch.db.NoteDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    private val adapter = NoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NoteDatabase.init(this)

        setupButtonAddNote()
        setupRecyclerView()

        noteViewModel = ViewModelProvider(this, getNoteViewModelFactory()).get(NoteViewModel::class.java)

        noteViewModel.getAllNotes().observe(this,
            Observer<List<Note>> { list ->
                adapter.setNotes(list)
            })

    }

    private fun setupButtonAddNote() {
        buttonAddNote.setOnClickListener {
            startActivityForResult(
                Intent(this, AddNoteActivity::class.java),
                ADD_NOTE_REQUEST
            )
        }
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.delete_all_notes -> {
                noteViewModel.deleteAllNotes()
                Toast.makeText(this, "All notes deleted!", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK){
            val newNote = Note(
                data!!.getStringExtra(AddNoteActivity.EXTRA_TITLE),
                data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
            )
            noteViewModel.insert(newNote)

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getNoteViewModelFactory() = object : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NoteViewModel() as T
        }

    }

    companion object {
        private const val ADD_NOTE_REQUEST = 1
    }
}
