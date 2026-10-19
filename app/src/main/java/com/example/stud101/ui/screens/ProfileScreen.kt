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
import com.example.stud101.data.ODRecord
import com.example.stud101.ui.theme.NeonLime
import com.example.stud101.ui.theme.RedWarning
import com.example.stud101.viewmodel.StudViewModel

@Composable
fun ProfileScreen(viewModel: StudViewModel) {
    val odRecords by viewModel.odRecords.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = NeonLime,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add OD")
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
                text = "Profile & OD Records",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (odRecords.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No OD records found.")
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(odRecords) { od ->
                        ODCard(
                            od = od,
                            onDelete = { viewModel.deleteODRecord(od) }
                        )
                    }
                }
            }
        }

        if (showDialog) {
            AddODDialog(
                onDismiss = { showDialog = false },
                onAdd = { date, reason, description ->
                    viewModel.addODRecord(date, reason, description)
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun ODCard(od: ODRecord, onDelete: () -> Unit) {
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
                Text(text = od.reason, style = MaterialTheme.typography.titleMedium, color = NeonLime)
                Text(text = "Date: ${od.date}", style = MaterialTheme.typography.bodyMedium)
                if (od.description.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = od.description, style = MaterialTheme.typography.bodySmall)
                }
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = RedWarning)
            }
        }
    }
}

@Composable
fun AddODDialog(
    onDismiss: () -> Unit,
    onAdd: (String, String, String) -> Unit
) {
    var date by remember { mutableStateOf("") }
    var reason by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add OD Record") },
        text = {
            Column {
                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Date") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = reason,
                    onValueChange = { reason = it },
                    label = { Text("Reason") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (date.isNotBlank() && reason.isNotBlank()) {
                        onAdd(date, reason, description)
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
