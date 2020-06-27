package com.cursoandroid.arch.activities

import android.app.Activity
import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.core.graphics.createBitmap
import com.cursoandroid.arch.R
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "com.cursoandroid.arch.activities.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.cursoandroid.arch.activities.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.cursoandroid.arch.activities.EXTRA_PRIORITY"
    }


    lateinit var editTextTitle: EditText
    lateinit var editTextDescription: EditText
    lateinit var numberPickerPriority: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        numberPickerPriority = findViewById(R.id.number_picker_priority)

        numberPickerPriority.minValue = 1
        numberPickerPriority.maxValue = 10

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)

        title = "Add Note Lino"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote() {
        if (edit_text_title.text.toString().trim()
                .isBlank() || edit_text_description.text.toString().trim().isBlank()
        ) {
            Toast.makeText(this, "Não pode inserir valor vazio", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_TITLE, edit_text_title.text.toString())
            putExtra(EXTRA_DESCRIPTION, edit_text_description.text.toString())
            putExtra(EXTRA_PRIORITY, number_picker_priority.value)
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
