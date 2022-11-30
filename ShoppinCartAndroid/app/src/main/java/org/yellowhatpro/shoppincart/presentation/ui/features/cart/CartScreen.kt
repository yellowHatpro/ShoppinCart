package org.yellowhatpro.shoppincart.presentation.ui.features.cart

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.filter
import org.yellowhatpro.shoppincart.presentation.ShoppinCartViewModel
import org.yellowhatpro.shoppincart.presentation.ui.components.NavigationItem

@Composable
fun CartScreen(modifier : Modifier) {
    Surface(modifier = modifier) {
        val viewModel = hiltViewModel<ShoppinCartViewModel>()
        val list = viewModel.allProducts.collectAsState().value.filter {
            it!!.id ==1 || it.id == 6 || it.id == 11 || it.id == 16 || it.id == 21
        }
        LazyColumn(){
            items(list){
                ProductItem(
                    id = it?.id ?: 0,
                    image = it?.images?.get(0) ?: "",
                    name = it?.title ?: "",
                    cost = it?.price,
                )
            }
        }
    }
}

@Composable
fun ProductItem(
    id : Int,
    image: String,
    name: String,
    cost: Int?,
) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .height(240.dp)
            .width(140.dp)
            .clip(RoundedCornerShape(10.dp))
           ,
    ) {
        Column(
            modifier = Modifier
                .matchParentSize(),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Fit,
            )
            Text(
                text = name,
                fontSize = 12.sp,
                style = LocalTextStyle.current.copy(lineHeight = 15.sp)
            )
            Text(
                text = "Rs. ${(cost ?: 0)*80}",
                fontSize = 12.sp
            )
        }
    }
}