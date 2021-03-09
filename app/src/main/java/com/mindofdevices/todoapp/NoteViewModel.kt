package com.mindofdevices.todoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allnotes : LiveData<List<notes>>
    private val repository: NoteRepository

    init {
        val dao = NodeDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allnotes = repository.allnotes
    }

    fun deleteNote(note: notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: notes) = viewModelScope.launch( Dispatchers.IO ) {
        repository.insert(note)
    }


}