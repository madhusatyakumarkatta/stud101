package com.example.stud101.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM subjects")
    fun getAllSubjects(): Flow<List<Subject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Update
    suspend fun updateSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    // Assignments
    @Query("SELECT * FROM assignments ORDER BY dueDate ASC")
    fun getAllAssignments(): Flow<List<Assignment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignment(assignment: Assignment)

    @Update
    suspend fun updateAssignment(assignment: Assignment)

    @Delete
    suspend fun deleteAssignment(assignment: Assignment)

    // Tests
    @Query("SELECT * FROM tests ORDER BY date ASC")
    fun getAllTests(): Flow<List<TestRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTest(test: TestRecord)

    @Delete
    suspend fun deleteTest(test: TestRecord)

    // OD Records
    @Query("SELECT * FROM od_records ORDER BY date DESC")
    fun getAllODRecords(): Flow<List<ODRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertODRecord(od: ODRecord)

    @Delete
    suspend fun deleteODRecord(od: ODRecord)
}
