package com.example.stud101.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.stud101.data.StudDatabase
import com.example.stud101.data.StudRepository
import com.example.stud101.data.Subject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudRepository

    val subjects: StateFlow<List<Subject>>

    init {
        val subjectDao = StudDatabase.getDatabase(application).subjectDao()
        repository = StudRepository(subjectDao)
        subjects = repository.allSubjects.stateIn(
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
}
