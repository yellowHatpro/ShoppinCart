package org.yellowhatpro.shoppincart.presentation.ui.features.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import org.yellowhatpro.shoppincart.presentation.ShoppinCartViewModel

@Composable
fun SelectedProductScreen(viewModel : ShoppinCartViewModel, id: Int, navHostController: NavHostController) {
    val product = viewModel.allProducts.collectAsState().value.last {
        it?.id == id
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(modifier = Modifier
                .height(250.dp)
                .width(250.dp)
                .clip(RoundedCornerShape(10.dp))
                .padding(10.dp)){
                AsyncImage(model = product?.images?.get(0) ?: 0, contentDescription = "",
                    contentScale = ContentScale.Crop, alignment = Alignment.TopCenter)
            }
        }
    }
}