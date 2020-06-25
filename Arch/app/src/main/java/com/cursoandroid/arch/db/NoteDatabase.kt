package com.cursoandroid.arch.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Note::class], version = 3, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        private var instance: NoteDatabase? = null

        fun getInstance() = instance

        @InternalCoroutinesApi
        fun init(context: Context): NoteDatabase {
            if (instance == null) {
                synchronized(NoteDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(
                    instance
                ).execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: NoteDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val noteDao = db?.noteDao

        override fun doInBackground(vararg params: Unit?) {
            noteDao?.insert(
                Note(
                    "title 1",
                    "descrip 1"
                )
            )
            noteDao?.insert(
                Note(
                    "title 2",
                    "descrip 2"
                )
            )
            noteDao?.insert(
                Note(
                    "title 3",
                    "descrip 3"
                )
            )

        }

    }

}