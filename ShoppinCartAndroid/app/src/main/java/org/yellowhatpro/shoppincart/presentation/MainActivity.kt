package org.yellowhatpro.shoppincart.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.yellowhatpro.shoppincart.presentation.ui.components.BottomBar
import org.yellowhatpro.shoppincart.presentation.theme.ShoppinCartTheme
import org.yellowhatpro.shoppincart.presentation.ui.components.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel = hiltViewModel<ShoppinCartViewModel>()
            ShoppinCartTheme {
                Scaffold(
                    topBar = {
                        TopAppBar()
                    }
                    ,
                    bottomBar = {
                        BottomBar(navController = navController)
                    }
                ) { paddingValues ->
                    ShoppinCartNavigation(
                        navHostController = navController,
                        viewModel = viewModel,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}