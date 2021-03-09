package com.mindofdevices.todoapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insert(note: notes)

    @Delete
    suspend fun delete(note: notes)

     @Query("SELECT * FROM notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<notes>>

}