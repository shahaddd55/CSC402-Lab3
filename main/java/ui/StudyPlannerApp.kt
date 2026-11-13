package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import sa.edu.iau.cshj.studyplanner.StudyTask
@Composable
fun StudyPlannerApp() {
    PlannerHomeRoute()
}

@Composable
fun PlannerHomeRoute() {
    val navController = rememberNavController()

    var uiState by remember {
        mutableStateOf(
            PlannerUiState(
                tasks = listOf(
                    StudyTask(1, "Complete Lab 7 assignment", "CSC 402"),
                    StudyTask(2, "Review Statistics chapter", "Statistics", true),
                    StudyTask(3, "Solve chapter exercises", "Physics"),
                    StudyTask(4, "Read Module 4", "IT 233"),
                    StudyTask(5, "Review financial ratios", "Finance")
                )
            )
        )
    }

    NavHost(
        navController = navController,
        startDestination = "planner_home"
    ) {
        composable("planner_home") {
            PlannerHomeScreen(
                uiState = uiState,
                onTaskClick = { taskId ->
                    navController.navigate("task_detail/$taskId")
                },
                onDoneChange = { taskId, isDone ->
                    uiState = uiState.copy(
                        tasks = uiState.tasks.map { task ->
                            if (task.id == taskId) {
                                task.copy(isDone = isDone)
                            } else {
                                task
                            }
                        }
                    )
                }
            )
        }

        composable(
            route = "task_detail/{taskId}",
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val taskId =
                backStackEntry.arguments?.getInt("taskId") ?: 0

            val selectedTask =
                uiState.tasks.firstOrNull { it.id == taskId }

            TaskDetailRoute(
                taskId = taskId,
                task = selectedTask,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}