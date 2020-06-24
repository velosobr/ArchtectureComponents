package com.cursoandroid.arch.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursoandroid.arch.db.Note
import com.cursoandroid.arch.adapter.NoteAdapter
import com.cursoandroid.arch.viewmodel.NoteViewModel
import com.cursoandroid.arch.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.android.inject


@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by inject()
    private val adapter: NoteAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButtonAddNote()
        setupRecyclerView()

        noteViewModel.getAllNotes().observe(this, Observer<List<Note>> { list ->
            list?.let {
                adapter.setNotes(it)
            }
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

    companion object {
        private const val ADD_NOTE_REQUEST = 1
    }
}
