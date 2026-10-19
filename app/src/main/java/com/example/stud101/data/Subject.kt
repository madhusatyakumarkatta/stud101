package com.example.stud101.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val attendedClasses: Int = 0,
    val totalClasses: Int = 0
) {
    val attendancePercentage: Float
        get() = if (totalClasses > 0) (attendedClasses.toFloat() / totalClasses) * 100 else 0f
}
