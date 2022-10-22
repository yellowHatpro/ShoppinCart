package org.yellowhatpro.shoppincart.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.yellowhatpro.shoppincart.presentation.ui.components.NavigationItem
import org.yellowhatpro.shoppincart.presentation.ui.features.home.HomeScreen
import org.yellowhatpro.shoppincart.presentation.ui.features.profile.ProfileScreen

@Composable
fun ShoppinCartNavigation(
    modifier : Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: ShoppinCartViewModel
    ) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationItem.Home.route
        ){
        composable(route = NavigationItem.Home.route){
            HomeScreen(modifier,
            viewModel)
        }
        composable(route = NavigationItem.Profile.route){
            ProfileScreen(modifier,
            viewModel)
        }
    }
}