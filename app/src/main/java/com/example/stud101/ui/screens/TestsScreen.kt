package com.example.stud101.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stud101.data.TestRecord
import com.example.stud101.ui.theme.NeonLime
import com.example.stud101.ui.theme.RedWarning
import com.example.stud101.viewmodel.StudViewModel

@Composable
fun TestsScreen(viewModel: StudViewModel) {
    val tests by viewModel.tests.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = NeonLime,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Test")
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
                text = "Test Tracker",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (tests.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No tests scheduled.")
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(tests) { test ->
                        TestCard(
                            test = test,
                            onDelete = { viewModel.deleteTest(test) }
                        )
                    }
                }
            }
        }

        if (showDialog) {
            AddTestDialog(
                onDismiss = { showDialog = false },
                onAdd = { subject, date, notes ->
                    viewModel.addTest(subject, date, notes)
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun TestCard(test: TestRecord, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = test.subject, style = MaterialTheme.typography.titleMedium, color = NeonLime)
                Text(text = "Date: ${test.date}", style = MaterialTheme.typography.bodyMedium)
                if (test.notes.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = test.notes, style = MaterialTheme.typography.bodySmall)
                }
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = RedWarning)
            }
        }
    }
}

@Composable
fun AddTestDialog(
    onDismiss: () -> Unit,
    onAdd: (String, String, String) -> Unit
) {
    var subject by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Test") },
        text = {
            Column {
                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    label = { Text("Subject") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Date") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes (optional)") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (subject.isNotBlank() && date.isNotBlank()) {
                        onAdd(subject, date, notes)
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
