package sa.edu.iau.cshj.studyplanner.ui
import sa.edu.iau.cshj.studyplanner.StudyTask
data class PlannerUiState(
    val greeting: String = "Welcome to your Study Planner",
    val tasks: List<StudyTask> = emptyList()
)