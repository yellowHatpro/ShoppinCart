package org.yellowhatpro.shoppincart.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint
import org.yellowhatpro.shoppincart.R
import org.yellowhatpro.shoppincart.presentation.ui.components.BottomBar
import org.yellowhatpro.shoppincart.presentation.theme.ShoppinCartTheme
import org.yellowhatpro.shoppincart.presentation.ui.components.TopAppBar
import org.yellowhatpro.shoppincart.presentation.ui.features.auth.AuthScreen
import org.yellowhatpro.shoppincart.presentation.ui.features.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = this.getSharedPreferences("uid", Context.MODE_PRIVATE)
        setContent {
            var authStatus by rememberSaveable {
                mutableStateOf(false)
            }
            if (!getSharedPreferences("uid", MODE_PRIVATE).getString("uid", "").isNullOrEmpty()) {
                authStatus = true
            }

            val navController = rememberNavController()
            val authViewModel = hiltViewModel<AuthViewModel>()
            val viewModel = hiltViewModel<ShoppinCartViewModel>()
            if (!authStatus) {
                AuthScreen(getGoogleLoginAuth(), sharedPreferences, authViewModel, this)
            } else {
                ShoppinCartTheme {
                    Scaffold(
                        topBar = {
                            TopAppBar(navController)
                        },
                        bottomBar = {
                            BottomBar(navController = navController)
                        }
                    ) { paddingValues ->

                        ShoppinCartNavigation(
                            navHostController = navController,
                            viewModel = viewModel,
                            modifier = Modifier.padding(paddingValues),
                            userId = getSharedPreferences("uid", MODE_PRIVATE).getString("uid", "")!!
                        )
                    }
                }
            }
        }
    }

    private fun getGoogleLoginAuth(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(R.string.google_cloud_server_client_id))
            .requestId()
            .requestProfile()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }
}