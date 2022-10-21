package org.yellowhatpro.shoppincart.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import org.yellowhatpro.shoppincart.presentation.ui.components.NavigationItem.Home
import org.yellowhatpro.shoppincart.presentation.ui.components.NavigationItem.Profile

@Composable
fun BottomBar(
    navController: NavController
) {
    val items = listOf(
        Home,
        Profile
    )
    val backStackEntry by navController.currentBackStackEntryAsState()
    NavigationBar {
        items.forEach{ item->
            val selected = item.route == backStackEntry?.destination?.route
            NavigationBarItem(
                selected = selected,
                icon = {Icon(item.icon, contentDescription = null)},
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}

sealed class NavigationItem(
    var route: String,
    var icon: ImageVector,
    var title: String
) {
    object Home: NavigationItem("home", Icons.Rounded.Home,"Home")
    object Profile: NavigationItem("profile", Icons.Rounded.Person, "Profile")
}