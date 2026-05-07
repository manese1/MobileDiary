package com.manese.mobilediary.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.manese.mobilediary.R
import androidx.navigation.compose.rememberNavController
import com.manese.mobilediary.navigation.ROUT_HOME
import com.manese.mobilediary.navigation.ROUT_LOGIN
import com.manese.mobilediary.navigation.ROUT_SPLASH
import com.manese.mobilediary.navigation.ROUT_TEACHER_REGISTER
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(2000)

        // TODO: replace with real auth check later
        val isLoggedIn = false

        if (isLoggedIn) {
            navController.navigate(ROUT_HOME) {
                popUpTo(ROUT_SPLASH) { inclusive = true }
            }
        } else {
            navController.navigate(ROUT_LOGIN) {
                popUpTo(ROUT_SPLASH) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1565C0),
                        Color(0xFF42A5F5)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "product",
            modifier = Modifier.size(100.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(rememberNavController())
}