package com.example.notepad

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.content_edit.*
import java.text.SimpleDateFormat
import java.util.*

class EditActivity : AppCompatActivity() {

    private lateinit var editViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        btnSave.setOnClickListener {

            editViewModel.note.value?.apply {
                title = etHeaderTitleText.text.toString()
                lastUpdated = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                text = etEditNoteText.text.toString()
            }

            editViewModel.updateNote()
        }
    }

    private fun initViewModel() {
        editViewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)
        editViewModel.note.value = intent.extras?.getParcelable(EXTRA_NOTE)!!

        editViewModel.note.observe(this, Observer { note ->
            if (note != null) {
                etHeaderTitleText.setText(note.title)
                etEditNoteText.setText(note.text)
            }
        })

        editViewModel.error.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        editViewModel.success.observe(this, Observer { success ->
            if (success) finish()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> { // Used to identify when the user has clicked the back button
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_NOTE = "EXTRA_NOTE"
    }
}
