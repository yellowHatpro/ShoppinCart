package org.yellowhatpro.shoppincart.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.yellowhatpro.shoppincart.presentation.ui.components.NavigationItem
import org.yellowhatpro.shoppincart.presentation.ui.features.cart.CartScreen
import org.yellowhatpro.shoppincart.presentation.ui.features.home.HomeScreen
import org.yellowhatpro.shoppincart.presentation.ui.features.item.SelectedProductScreen
import org.yellowhatpro.shoppincart.presentation.ui.features.profile.ProfileScreen

@Composable
fun ShoppinCartNavigation(
    modifier : Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: ShoppinCartViewModel,
    userId: String
    ) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationItem.Home.route
    ) {
        composable(route = NavigationItem.Home.route) {
            HomeScreen(
                modifier,
                viewModel,
                navHostController,
                userId
            )
        }
        composable(route = NavigationItem.Profile.route) {
            ProfileScreen(
                modifier,
                viewModel
            )
        }
        composable(route = NavigationItem.Cart.route) {
            CartScreen(modifier)
        }
        composable(
            route = "${NavigationItem.Home.route}/{id}", arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            it.arguments?.getInt("id")?.let { id ->
                SelectedProductScreen(navHostController = navHostController, id = id, viewModel = viewModel)
            }
        }
    }
}