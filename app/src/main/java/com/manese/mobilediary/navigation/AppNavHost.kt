package com.manese.mobilediary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manese.mobilediary.ui.screens.admin.AssignStudentClassScreen
import com.manese.mobilediary.ui.screens.admin.CreateClassScreen
import com.manese.mobilediary.ui.screens.announcements.AnnouncementScreen
import com.manese.mobilediary.ui.screens.announcements.UploadAnnouncementScreen
import com.manese.mobilediary.ui.screens.auth.LoginScreen
import com.manese.mobilediary.ui.screens.auth.RegisterScreen
import com.manese.mobilediary.ui.screens.auth.TeacherRegisterScreen
import com.manese.mobilediary.ui.screens.home.HomeScreen
import com.manese.mobilediary.ui.screens.homework.HomeworkScreen
import com.manese.mobilediary.ui.screens.homework.UploadHomeworkScreen
import com.manese.mobilediary.ui.screens.links.LinkAccountsScreen
import com.manese.mobilediary.ui.screens.profile.ProfileScreen
import com.manese.mobilediary.ui.screens.remarks.RemarksScreen
import com.manese.mobilediary.ui.screens.remarks.UploadRemarksScreen
import com.manese.mobilediary.ui.screens.splash.SplashScreen
import com.manese.mobilediary.ui.screens.teacher.RegisterStudentScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_ANNOUNCEMENTS) {
            AnnouncementScreen(navController)
        }
        composable(ROUT_HOMEWORK) {
            HomeworkScreen(navController)
        }
        composable("$ROUT_PROFILE/{userName}/{role}") { backStackEntry ->

            val userName =
                backStackEntry.arguments?.getString("userName") ?: "User"

            val role =
                backStackEntry.arguments?.getString("role") ?: "STUDENT"

            ProfileScreen(
                navController = navController,
                userName = userName,
                role = role
            )
        }
        composable(ROUT_REMARKS) {
            RemarksScreen(navController)
        }
        composable("$ROUT_HOME/{userName}/{role}") { backStackEntry ->

            val userName = backStackEntry.arguments?.getString("userName") ?: "User"
            val role = backStackEntry.arguments?.getString("role") ?: "STUDENT"

            HomeScreen(
                navController = navController,
                userName = userName,
                role = role
            )
        }
        composable(ROUT_TEACHER_REGISTER) {
            TeacherRegisterScreen(navController)
        }
        composable(ROUT_REGISTER_STUDENT) {
            RegisterStudentScreen(navController)
        }
        composable(ROUT_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUT_UPLOAD_HOMEWORK) {
            UploadHomeworkScreen(navController)
        }
        composable(ROUT_UPLOAD_ANNOUNCEMENT) {
            UploadAnnouncementScreen(navController)
        }
        composable(ROUT_UPLOAD_REMARKS) {
            UploadRemarksScreen(navController)
        }
        composable(ROUT_LINK_ACCOUNTS) {
            LinkAccountsScreen(navController)
        }
        composable(ROUT_CREATE_CLASS) {
            CreateClassScreen(navController)
        }
        composable(ROUT_ASSIGN_CLASS) {
            AssignStudentClassScreen(navController)
        }
    }
}