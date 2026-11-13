package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.StudyTask
@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onTaskClick: (Int) -> Unit,
    onDoneChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val completedTasks = uiState.tasks.count { task: StudyTask -> task.isDone }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = uiState.greeting,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "$completedTasks of ${uiState.tasks.size} done",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(count = uiState.tasks.size) { index ->

                val task = uiState.tasks[index]

                TaskItem(
                    task = task,
                    onTaskClick = onTaskClick,
                    onDoneChange = onDoneChange
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Task Item Not Done")
@Composable
private fun TaskItemNotDonePreview() {
    MaterialTheme {
        TaskItem(
            task = StudyTask(
                id = 1,
                title = "Complete Lab 7 assignment",
                course = "CSC 402",
                isDone = false
            ),
            onTaskClick = {},
            onDoneChange = { _, _ -> }
        )
    }
}

@Preview(showBackground = true, name = "Task Item Done")
@Composable
private fun TaskItemDonePreview() {
    MaterialTheme {
        TaskItem(
            task = StudyTask(
                id = 2,
                title = "Review Statistics chapter",
                course = "Statistics",
                isDone = true
            ),
            onTaskClick = {},
            onDoneChange = { _, _ -> }
        )
    }
}

@Preview(showBackground = true, name = "Planner Home With Five Tasks")
@Composable
private fun PlannerHomeScreenPreview() {
    MaterialTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                greeting = "Welcome to your Study Planner",
                tasks = listOf(
                    StudyTask(1, "Complete Lab 7 assignment", "CSC 402"),
                    StudyTask(2, "Review Statistics chapter", "Statistics", true),
                    StudyTask(3, "Solve chapter exercises", "Physics"),
                    StudyTask(4, "Read Module 4", "IT 233"),
                    StudyTask(5, "Review financial ratios", "Finance")
                )
            ),
            onTaskClick = {},
            onDoneChange = { _, _ -> }
        )
    }
}