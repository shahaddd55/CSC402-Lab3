package edu.iau.cshj.csc402.lab3.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class StudyTask(val title: String, val isCompleted: Boolean)

data class PlannerUiState(
    val studentName: String = "Student",
    val tasks: List<StudyTask> = listOf(
        StudyTask("Lab 4 report", true),
        StudyTask("Lab 5 report", false),
        StudyTask("Study for Quiz", false)
    )
)

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onTaskClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val completedCount = uiState.tasks.count { it.isCompleted }
    val totalCount = uiState.tasks.size

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Welcome, ${uiState.studentName}!",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Completed tasks: $completedCount of $totalCount done",
            style = MaterialTheme.typography.bodyLarge
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(uiState.tasks) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onTaskClick(task.title) }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = task.title, style = MaterialTheme.typography.bodyLarge)
                        Text(
                            text = if (task.isCompleted) "Done" else "Pending",
                            color = if (task.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlannerHomeRoute(onTaskClick: (String) -> Unit) {
    PlannerHomeScreen(
        uiState = PlannerUiState(),
        onTaskClick = onTaskClick
    )
}

@Preview(showBackground = true)
@Composable
fun PlannerHomePreview() {
    PlannerHomeScreen(uiState = PlannerUiState(), onTaskClick = {})
}