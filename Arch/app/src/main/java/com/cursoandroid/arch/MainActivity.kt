package com.cursoandroid.arch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi
import android.content.Context
import android.os.AsyncTask


@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val ADD_NOTE_REQUEST = 1
    private lateinit var noteViewModel: NoteViewModel
    private val adapter = NoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButtonAddNote()
        setupRecyclerView()

        noteViewModel = ViewModelProvider(this)

    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }

    private fun setupButtonAddNote() {
        buttonAddNote.setOnClickListener {
            startActivityForResult(
                Intent(this, AddNoteActivity::class.java),
                ADD_NOTE_REQUEST
            )
        }
    }
}
