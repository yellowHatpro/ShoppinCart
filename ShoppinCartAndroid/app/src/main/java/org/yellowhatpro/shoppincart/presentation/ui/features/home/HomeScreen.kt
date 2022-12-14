package org.yellowhatpro.shoppincart.presentation.ui.features.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import org.yellowhatpro.shoppincart.data.entities.Category.*
import org.yellowhatpro.shoppincart.data.entities.categories
import org.yellowhatpro.shoppincart.presentation.ShoppinCartViewModel
import org.yellowhatpro.shoppincart.presentation.ui.components.NavigationItem
import org.yellowhatpro.shoppincart.presentation.ui.features.cart.CartViewModel

@Composable
fun HomeScreen(
    modifier : Modifier,
    viewModel : ShoppinCartViewModel,
    navHostController: NavHostController,
    userId: String
) {
    val cartViewModel = hiltViewModel<CartViewModel>()
    cartViewModel.getId(userId)
    Log.d("userId",userId)
    Surface(modifier = modifier.fillMaxSize()) {
        val smartphones by viewModel.smartphones.collectAsState()
        val laptop by viewModel.laptop.collectAsState()
        val fragrances by viewModel.fragrances.collectAsState()
        val skincare by viewModel.skincare.collectAsState()
        val groceries by viewModel.groceries.collectAsState()
        val homeDecoration by viewModel.homeDecoration.collectAsState()
        val furniture by viewModel.furniture.collectAsState()
        val tops by viewModel.tops.collectAsState()
        val womensDresses by viewModel.womensDresses.collectAsState()
        val womenShoes by viewModel.womenShoes.collectAsState()
        val mensShirts by viewModel.mensShirts.collectAsState()
        val mensShoes by viewModel.mensShoes.collectAsState()
        val mensWatches by viewModel.mensWatches.collectAsState()
        val womensWatches by viewModel.womensWatches.collectAsState()
        val womensBags by viewModel.womensBags.collectAsState()
        val womensJewellery by viewModel.womensJewellery.collectAsState()
        val sunglasses by viewModel.sunglasses.collectAsState()
        val automotives by viewModel.automotives.collectAsState()
        val motorcycle by viewModel.motorcycle.collectAsState()
        val lighting by viewModel.lighting.collectAsState()
        LazyColumn {
            items(categories) { category ->
                Column {
                    Text(text = category.name)
                    LazyRow {
                        items(
                            when (category) {
                                Smartphones -> smartphones
                                Laptops -> laptop
                                Fragrances -> fragrances
                                Skincare -> skincare
                                Groceries -> groceries
                                HomeDecoration -> homeDecoration
                                Furniture -> furniture
                                Tops -> tops
                                WomensDresses -> womensDresses
                                WomensShoes -> womenShoes
                                MensShirts -> mensShirts
                                MensShoes -> mensShoes
                                MensWatches -> mensWatches
                                WomensWatches -> womensWatches
                                WomensBags -> womensBags
                                WomensJewellery -> womensJewellery
                                Sunglasses -> sunglasses
                                Automotive -> automotives
                                Motorcycle -> motorcycle
                                Lighting -> lighting
                            }
                        ) {
                            ProductItem(
                                id = it?.id ?: 0 ,
                                image = it?.images?.get(0) ?: "",
                                name = it?.title ?: "",
                                cost = it?.price,
                                navHostController = navHostController,
                                cartViewModel = cartViewModel,
                                userId
                            )
                        }
                    }
                }
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
    navHostController: NavHostController,
    cartViewModel : CartViewModel,
    userId: String
) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .height(240.dp)
            .width(140.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navHostController.navigate(NavigationItem.Home.route + "/" + id)
            },
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
            Icon(imageVector = Icons.Rounded.Save, contentDescription = "",modifier = Modifier.clickable {
                cartViewModel.addToCart(id.toString(), userId)
            })
        }
    }
}