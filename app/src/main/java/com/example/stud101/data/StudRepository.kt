package com.example.stud101.data

import kotlinx.coroutines.flow.Flow

class StudRepository(private val appDao: AppDao) {
    val allSubjects: Flow<List<Subject>> = appDao.getAllSubjects()
    val allAssignments: Flow<List<Assignment>> = appDao.getAllAssignments()
    val allTests: Flow<List<TestRecord>> = appDao.getAllTests()
    val allODRecords: Flow<List<ODRecord>> = appDao.getAllODRecords()

    suspend fun insertSubject(subject: Subject) = appDao.insertSubject(subject)
    suspend fun updateSubject(subject: Subject) = appDao.updateSubject(subject)
    suspend fun deleteSubject(subject: Subject) = appDao.deleteSubject(subject)

    suspend fun insertAssignment(assignment: Assignment) = appDao.insertAssignment(assignment)
    suspend fun updateAssignment(assignment: Assignment) = appDao.updateAssignment(assignment)
    suspend fun deleteAssignment(assignment: Assignment) = appDao.deleteAssignment(assignment)

    suspend fun insertTest(test: TestRecord) = appDao.insertTest(test)
    suspend fun deleteTest(test: TestRecord) = appDao.deleteTest(test)

    suspend fun insertODRecord(od: ODRecord) = appDao.insertODRecord(od)
    suspend fun deleteODRecord(od: ODRecord) = appDao.deleteODRecord(od)
}
