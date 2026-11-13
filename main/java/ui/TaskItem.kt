package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.StudyTask
@Composable
fun TaskItem(
    task: StudyTask,
    onTaskClick: (Int) -> Unit,
    onDoneChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTaskClick(task.id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.isDone)
                        TextDecoration.LineThrough
                    else TextDecoration.None
                )

                Text(
                    text = task.course,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Checkbox(
                checked = task.isDone,
                onCheckedChange = { checked ->
                    onDoneChange(task.id, checked)
                }
            )
        }
    }
}