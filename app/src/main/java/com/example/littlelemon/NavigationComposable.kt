package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.reflect.KFunction0


@Composable
fun Navigation(
    navController: NavHostController,
    readPreferences: () -> Map<String, String>,
    savePreferences: (firstName : String, lastName : String, email : String) -> Unit,
    databaseMenuItems: List<MenuItemRoom>) {
    val navController = rememberNavController()
    val isNewUser = readPreferences()["firstName"] == "";
    NavHost(navController = navController, startDestination = if (isNewUser) { Onboarding.route } else { Home.route }) {
        composable(Onboarding.route) {
            Onboarding(navController, savePreferences)
        }
        composable(Home.route) {
            Image(navController, readPreferences, databaseMenuItems)
        }
        composable(Profile.route) {
            ProfileScreen(navController, readPreferences, savePreferences)
        }
    }
}
