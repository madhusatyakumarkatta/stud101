package com.example.stud101.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tests")
data class TestRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val subject: String,
    val date: String,
    val notes: String
)
