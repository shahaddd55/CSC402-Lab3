

package sa.edu.iau.cshj.studyplanner.ui

data class PlannerUiState(
    val greeting: String = "Hello Student",
    val totalTasks: Int = 0,
    val completedTasks: Int = 0,
    val tasks: List<String> = emptyList()
)

