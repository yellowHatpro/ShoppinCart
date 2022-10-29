package org.yellowhatpro.shoppincart.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    navHostController: NavHostController
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Icon(imageVector = Icons.Rounded.ShoppingBag, contentDescription = "")
                Text(
                    text = "SHOPPIN CART",
                    fontSize = 18.sp
                )
            }
        },
        actions = {
            var showMenu by remember {
                mutableStateOf(false)
            }
            IconButton(onClick = {
                navHostController.navigate(NavigationItem.Cart.route)
            }
            ) {
                Icon(
                    imageVector = Icons.Rounded.ShoppingCart,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = {
                showMenu= false
            }) {
                DropdownMenuItem(onClick = {  }, text = {
                    Text(text = "About")
                })
            }
        }
    )
}