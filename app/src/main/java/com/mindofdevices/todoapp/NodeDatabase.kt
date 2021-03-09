package com.mindofdevices.todoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mindofdevices.todoapp.NodeDatabase as NodeDatabase

@Database(entities = arrayOf(notes::class), version = 1, exportSchema = false)
abstract class NodeDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NodeDatabase? = null

        fun getDatabase(context: Context): NodeDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NodeDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}