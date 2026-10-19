package com.example.stud101.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stud101.ui.theme.NeonLime
import com.example.stud101.ui.theme.RedWarning
import com.example.stud101.viewmodel.StudViewModel

@Composable
fun DashboardScreen(viewModel: StudViewModel) {
    val subjects by viewModel.subjects.collectAsState()
    val assignments by viewModel.assignments.collectAsState()
    val tests by viewModel.tests.collectAsState()

    val overallAttendance = if (subjects.isNotEmpty()) {
        val totalAttended = subjects.sumOf { it.attendedClasses }
        val totalClasses = subjects.sumOf { it.totalClasses }
        if (totalClasses > 0) (totalAttended.toFloat() / totalClasses) * 100 else 0f
    } else {
        0f
    }

    val pendingAssignments = assignments.filter { !it.isCompleted }
    val upcomingTests = tests

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                androidx.compose.foundation.Image(
                    painter = androidx.compose.ui.res.painterResource(id = com.example.stud101.R.drawable.ic_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "STUD101",
                    style = MaterialTheme.typography.titleLarge,
                    color = NeonLime,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Welcome back!", style = MaterialTheme.typography.titleMedium)
        }

        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Overall Attendance", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${String.format("%.1f", overallAttendance)}%",
                        style = MaterialTheme.typography.titleLarge,
                        color = if (overallAttendance >= 75f) NeonLime else RedWarning
                    )
                    
                    if (subjects.isEmpty()) {
                        Text(
                            text = "Add subjects in Attendance tab to see your overall attendance.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else if (overallAttendance < 75f) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Warning, contentDescription = "Warning", tint = RedWarning)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Warning: Attendance below 75%",
                                color = RedWarning,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    } else {
                        Text(
                            text = "You are in the safe zone! Keep it up.",
                            color = NeonLime,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        if (pendingAssignments.isNotEmpty()) {
            item {
                Text(text = "Pending Assignments", style = MaterialTheme.typography.titleMedium)
            }
            items(pendingAssignments) { assignment ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = assignment.title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                        Text(text = "${assignment.subject} - Due: ${assignment.dueDate}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        if (upcomingTests.isNotEmpty()) {
            item {
                Text(text = "Upcoming Tests", style = MaterialTheme.typography.titleMedium)
            }
            items(upcomingTests) { test ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = test.subject, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = NeonLime)
                        Text(text = "Date: ${test.date}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        if (pendingAssignments.isEmpty() && upcomingTests.isEmpty()) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("No upcoming events or pending assignments.", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}
