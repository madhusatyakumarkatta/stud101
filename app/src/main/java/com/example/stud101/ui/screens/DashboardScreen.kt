package com.example.stud101.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stud101.ui.theme.NeonLime
import com.example.stud101.ui.theme.RedWarning
import com.example.stud101.viewmodel.StudViewModel

@Composable
fun DashboardScreen(viewModel: StudViewModel) {
    val subjects by viewModel.subjects.collectAsState()

    val overallAttendance = if (subjects.isNotEmpty()) {
        val totalAttended = subjects.sumOf { it.attendedClasses }
        val totalClasses = subjects.sumOf { it.totalClasses }
        if (totalClasses > 0) (totalAttended.toFloat() / totalClasses) * 100 else 0f
    } else {
        0f
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "STUD101",
                style = MaterialTheme.typography.titleLarge,
                color = NeonLime,
                fontWeight = FontWeight.ExtraBold
            )
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
                    if (overallAttendance < 75f && subjects.isNotEmpty()) {
                        Text(
                            text = "Warning: Attendance below 75%",
                            color = RedWarning,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        item {
            Text(text = "Upcoming Deadlines & Tests", style = MaterialTheme.typography.titleMedium)
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("No upcoming events.", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
