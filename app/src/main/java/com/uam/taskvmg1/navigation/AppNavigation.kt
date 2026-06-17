    package com.uam.taskvmg1.navigation

    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.lifecycle.viewmodel.compose.viewModel
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import androidx.navigation.toRoute
    import com.uam.taskvmg1.screen.TaskDetailScreen
    import com.uam.taskvmg1.screen.TaskListScreen
    import com.uam.taskvmg1.service.ServiceLocator
    import com.uam.taskvmg1.vmodel.TaskListViewModel
    import com.uam.taskvmg1.vmodel.TaskListViewModelFactory

    @Composable
    fun AppNavigation(modifier: Modifier)
    {
        val navController = rememberNavController()

        NavHost(navController = navController
            , startDestination = TaskList
        )
        {
            composable<TaskList>
            {
                val viewModel : TaskListViewModel = viewModel(
                    factory = TaskListViewModelFactory(
                        ServiceLocator.repository
                    )
                )
                TaskListScreen(navController = navController,viewModel)
            }
            composable<TaskDetail>{ backStackEntry ->
                val route = backStackEntry.toRoute<TaskDetail>()
                TaskDetailScreen(navController = navController,
                    taskId = route.taskId)
            }

        }
    }