package com.example.stud101.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.stud101.data.Subject
import com.example.stud101.ui.theme.NeonLime
import com.example.stud101.ui.theme.RedWarning
import com.example.stud101.viewmodel.StudViewModel

@Composable
fun AttendanceScreen(viewModel: StudViewModel) {
    val subjects by viewModel.subjects.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = NeonLime,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Subject")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Attendance Tracker",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (subjects.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No subjects added yet. Tap + to add one.")
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(subjects) { subject ->
                        SubjectCard(
                            subject = subject,
                            onUpdate = { viewModel.updateSubject(it) },
                            onDelete = { viewModel.deleteSubject(subject) }
                        )
                    }
                }
            }
        }

        if (showDialog) {
            AddSubjectDialog(
                onDismiss = { showDialog = false },
                onAdd = { name, attended, total ->
                    viewModel.addSubject(name, attended, total)
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun SubjectCard(subject: Subject, onUpdate: (Subject) -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = subject.name, style = MaterialTheme.typography.titleMedium)
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = RedWarning)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            val percentage = subject.attendancePercentage
            val isSafe = percentage >= 75f
            
            Text(
                text = "Attendance: ${String.format("%.1f", percentage)}%",
                color = if (isSafe) NeonLime else RedWarning,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))
            
            // Bunk calculator logic
            if (isSafe) {
                val canBunk = calculateBunksAvailable(subject.attendedClasses, subject.totalClasses)
                Text(text = "Safe to bunk: $canBunk classes", style = MaterialTheme.typography.bodyMedium)
            } else {
                val needed = calculateClassesNeeded(subject.attendedClasses, subject.totalClasses)
                Text(text = "Classes needed for 75%: $needed", color = RedWarning, style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        onUpdate(subject.copy(totalClasses = subject.totalClasses + 1))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RedWarning)
                ) {
                    Text("Missed", color = MaterialTheme.colorScheme.onPrimary)
                }
                
                Button(
                    onClick = {
                        onUpdate(subject.copy(
                            attendedClasses = subject.attendedClasses + 1,
                            totalClasses = subject.totalClasses + 1
                        ))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = NeonLime)
                ) {
                    Text("Attended", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}

fun calculateBunksAvailable(attended: Int, total: Int): Int {
    if (total == 0) return 0
    var bunks = 0
    var currentTotal = total
    while ((attended.toFloat() / (currentTotal + 1)) >= 0.75f) {
        bunks++
        currentTotal++
    }
    return bunks
}

fun calculateClassesNeeded(attended: Int, total: Int): Int {
    if ((attended.toFloat() / total) >= 0.75f) return 0
    var needed = 0
    var currentAttended = attended
    var currentTotal = total
    while ((currentAttended.toFloat() / currentTotal) < 0.75f) {
        needed++
        currentAttended++
        currentTotal++
    }
    return needed
}

@Composable
fun AddSubjectDialog(
    onDismiss: () -> Unit,
    onAdd: (String, Int, Int) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var attended by remember { mutableStateOf("") }
    var total by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Subject") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Subject Name") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = attended,
                    onValueChange = { attended = it },
                    label = { Text("Classes Attended") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = total,
                    onValueChange = { total = it },
                    label = { Text("Total Classes") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val attendedInt = attended.toIntOrNull() ?: 0
                    val totalInt = total.toIntOrNull() ?: 0
                    if (name.isNotBlank()) {
                        onAdd(name, attendedInt, totalInt)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = NeonLime)
            ) {
                Text("Add", color = MaterialTheme.colorScheme.onPrimary)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
