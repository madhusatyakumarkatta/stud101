package com.example.stud101.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.stud101.ui.screens.AssignmentsScreen
import com.example.stud101.ui.screens.AttendanceScreen
import com.example.stud101.ui.screens.DashboardScreen
import com.example.stud101.ui.screens.ProfileScreen
import com.example.stud101.ui.screens.TestsScreen
import com.example.stud101.ui.theme.NeonLime
import com.example.stud101.viewmodel.StudViewModel

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Home", Icons.Default.Home)
    object Attendance : Screen("attendance", "Attendance", Icons.Default.CheckCircle)
    object Assignments : Screen("assignments", "Tasks", Icons.Default.List)
    object Tests : Screen("tests", "Tests", Icons.Default.DateRange)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
}

@Composable
fun StudApp() {
    val navController = rememberNavController()
    val viewModel: StudViewModel = viewModel()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) {
                DashboardScreen(viewModel)
            }
            composable(Screen.Attendance.route) {
                AttendanceScreen(viewModel)
            }
            composable(Screen.Assignments.route) {
                AssignmentsScreen(viewModel)
            }
            composable(Screen.Tests.route) {
                TestsScreen(viewModel)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(viewModel)
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Screen.Dashboard,
        Screen.Attendance,
        Screen.Assignments,
        Screen.Tests,
        Screen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = androidx.compose.material3.MaterialTheme.colorScheme.background,
                    selectedTextColor = NeonLime,
                    indicatorColor = NeonLime
                )
            )
        }
    }
}
