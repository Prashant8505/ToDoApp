package com.mindofdevices.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.renderscript.ScriptGroup
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), InotesRVadapter {

    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRvAdapter(this, this)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this,
                    ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allnotes.observe(this, Observer {list ->
            list?.let {
                adapter.updateList(it)
            }
        })


    }

    override fun onitemClicked(note: notes) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "Item is deleted", Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View){
        val inputs = findViewById<EditText>(R.id.input)
        val noteText = inputs.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(notes(noteText))
        }
    }
}