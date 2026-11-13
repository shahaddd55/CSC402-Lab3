package sa.edu.iau.cshj.studyplanner.ui

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.StudyTask
data class TaskDetailUiState(
    val taskId: Int = 0,
    val title: String = "",
    val course: String = "",
    val isDone: Boolean = false
)

@Composable
fun TaskDetailScreen(
    uiState: TaskDetailUiState,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Task Details",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = uiState.title,
                style = MaterialTheme.typography.titleLarge
            )

            Text(text = "Course: ${uiState.course}")
            Text(text = "Task ID: ${uiState.taskId}")

            Text(
                text = if (uiState.isDone)
                    "Completed"
                else
                    "Not completed"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onShareClick) {
                Text("Share")
            }

            OutlinedButton(onClick = onBackClick) {
                Text("Back")
            }
        }
    }
}

@Composable
fun TaskDetailRoute(
    taskId: Int,
    task: StudyTask?,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    val uiState = TaskDetailUiState(
        taskId = taskId,
        title = task?.title ?: "Task $taskId",
        course = task?.course ?: "",
        isDone = task?.isDone ?: false
    )

    TaskDetailScreen(
        uiState = uiState,
        onShareClick = {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "I'm studying ${uiState.title} for ${uiState.course}."
                )
            }

            context.startActivity(
                Intent.createChooser(shareIntent, "Share task")
            )
        },
        onBackClick = onBackClick
    )
}