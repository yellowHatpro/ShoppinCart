package org.yellowhatpro.shoppincart.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(){
    TopAppBar(
        title = {
            Text(text = "SHOPPIN CART",
            modifier = Modifier.padding(10.dp),
            fontSize = 18.sp)
        },
        actions = {
            Icon(imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = "",
                modifier = Modifier.padding(10.dp))
        }
    )
}