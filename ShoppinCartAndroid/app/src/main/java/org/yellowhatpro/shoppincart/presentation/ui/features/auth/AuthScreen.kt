package org.yellowhatpro.shoppincart.presentation.ui.features.auth

import android.app.Activity
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import org.yellowhatpro.shoppincart.presentation.MainActivity


@Composable
fun AuthScreen(getGoogleSignInClient : GoogleSignInClient?, sharedPreferences: SharedPreferences, authViewModel : AuthViewModel, activity: Activity) {
    Log.d("whatisthis",getGoogleSignInClient?.signInIntent.toString())
    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult: ActivityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val intent = activityResult.data
                if (activityResult.data != null) {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(intent)
                    handleSignInResult(task, sharedPreferences, activity, authViewModel)
                    Log.d("Login","okay")
                } else {
                    Log.d("Login","error")
                }
            } else {
                Log.d("activityResult",activityResult.resultCode.toString())
            }
        }
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                startForResult.launch(getGoogleSignInClient?.signInIntent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            )
        ) {
            Text(text = "Sign in with Google", modifier = Modifier.padding(6.dp))
        }
    }

}

fun handleSignInResult(completedTask: Task<GoogleSignInAccount>, sharedPreferences: SharedPreferences, activity: Activity, authViewModel: AuthViewModel) {
    try {
        val account = completedTask.getResult(ApiException::class.java)

        // Signed in successfully, show authenticated UI.
        updateUI(account, sharedPreferences, activity)
        authViewModel.addUserToFirebase(account.id.toString())
    } catch (e: ApiException) {
        // The ApiException status code indicates the detailed failure reason.
        // Please refer to the GoogleSignInStatusCodes class reference for more information.
        Log.w("SignIn", "signInResult:failed code=" + e.statusCode)
        updateUI(null, sharedPreferences, activity)
    }
}

private fun updateUI(googleSignInAccount: GoogleSignInAccount?, sharedPreferences: SharedPreferences,activity: Activity){
    if (googleSignInAccount!=null){
        sharedPreferences.edit().putString("uid", googleSignInAccount.id).apply()
        Toast.makeText(activity, "Sign in Successful", Toast.LENGTH_SHORT).show()
        (activity as MainActivity).recreate()
    }
}



