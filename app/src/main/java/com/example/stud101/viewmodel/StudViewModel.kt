package com.example.stud101.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.stud101.data.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudRepository

    val subjects: StateFlow<List<Subject>>
    val assignments: StateFlow<List<Assignment>>
    val tests: StateFlow<List<TestRecord>>
    val odRecords: StateFlow<List<ODRecord>>

    init {
        val appDao = StudDatabase.getDatabase(application).appDao()
        repository = StudRepository(appDao)
        
        subjects = repository.allSubjects.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        
        assignments = repository.allAssignments.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        
        tests = repository.allTests.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        
        odRecords = repository.allODRecords.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun addSubject(name: String, attended: Int, total: Int) {
        viewModelScope.launch {
            repository.insertSubject(Subject(name = name, attendedClasses = attended, totalClasses = total))
        }
    }

    fun updateSubject(subject: Subject) {
        viewModelScope.launch {
            repository.updateSubject(subject)
        }
    }

    fun deleteSubject(subject: Subject) {
        viewModelScope.launch {
            repository.deleteSubject(subject)
        }
    }

    fun addAssignment(subject: String, title: String, dueDate: String) {
        viewModelScope.launch {
            repository.insertAssignment(Assignment(subject = subject, title = title, dueDate = dueDate))
        }
    }

    fun updateAssignment(assignment: Assignment) {
        viewModelScope.launch {
            repository.updateAssignment(assignment)
        }
    }

    fun deleteAssignment(assignment: Assignment) {
        viewModelScope.launch {
            repository.deleteAssignment(assignment)
        }
    }

    fun addTest(subject: String, date: String, notes: String) {
        viewModelScope.launch {
            repository.insertTest(TestRecord(subject = subject, date = date, notes = notes))
        }
    }

    fun deleteTest(test: TestRecord) {
        viewModelScope.launch {
            repository.deleteTest(test)
        }
    }

    fun addODRecord(date: String, reason: String, description: String) {
        viewModelScope.launch {
            repository.insertODRecord(ODRecord(date = date, reason = reason, description = description))
        }
    }

    fun deleteODRecord(od: ODRecord) {
        viewModelScope.launch {
            repository.deleteODRecord(od)
        }
    }
}
