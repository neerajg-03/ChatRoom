package com.example.chatroom

import Screen.ChatRoomListScreen
import Screen.LoginScreen
import Screen.SignUpScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatroom.ui.theme.ChatRoomTheme
import com.example.chatroom.viewModel.AuthViewModel
import com.example.chatroom.viewModel.RoomViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatRoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController= rememberNavController()
                    val authViewModel: AuthViewModel = viewModel()
                    val roomViewModel:RoomViewModel= viewModel()
                    NavigationGraph(navController = navController,authViewModel)
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignupScreen.route
    ) {
        composable(Screen.SignupScreen.route) {
            SignUpScreen(authViewModel=authViewModel,

                onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route)},
                onNavigateToChatRoom1 = {navController.navigate(Screen.ChatRoomsScreen.route)}
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(

                authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.SignupScreen.route) },
                onNavigateToChatRoom = {navController.navigate(Screen.ChatRoomsScreen.route)}

               )
        }
        composable(Screen.ChatRoomsScreen.route) {
            ChatRoomListScreen()
        }
        }
    }
