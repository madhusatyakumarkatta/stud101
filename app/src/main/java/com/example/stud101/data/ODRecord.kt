package com.example.stud101.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "od_records")
data class ODRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val reason: String,
    val description: String
)
