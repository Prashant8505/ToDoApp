package com.mindofdevices.todoapp

import android.provider.ContactsContract
import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val allnotes: LiveData<List<notes>> = noteDao.getAllNotes()

    suspend fun insert(note: notes){
        noteDao.insert(note)
    }

    suspend fun delete(note: notes){
        noteDao.delete(note)
    }
}