package com.example.stud101.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subject::class], version = 1, exportSchema = false)
abstract class StudDatabase : RoomDatabase() {
    abstract fun subjectDao(): SubjectDao

    companion object {
        @Volatile
        private var Instance: StudDatabase? = null

        fun getDatabase(context: Context): StudDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, StudDatabase::class.java, "stud_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
