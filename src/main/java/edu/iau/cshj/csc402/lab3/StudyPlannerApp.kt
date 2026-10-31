package edu.iau.cshj.csc402.lab3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import edu.iau.cshj.csc402.lab3.ui.*

@Composable
fun StudyPlannerApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PlannerHome) {
        composable<PlannerHome> {
            PlannerHomeRoute(
                onTaskClick = { taskTitle ->
                    navController.navigate(TaskDetail(taskTitle))
                }
            )
        }
        composable<TaskDetail> { backStackEntry ->
            val detail: TaskDetail = backStackEntry.toRoute()
            TaskDetailRoute(
                title = detail.title,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}