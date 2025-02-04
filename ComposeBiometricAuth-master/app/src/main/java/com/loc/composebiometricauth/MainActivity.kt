package com.loc.composebiometricauth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.loc.composebiometricauth.ui.theme.ComposeBiometricAuthTheme

class MainActivity : FragmentActivity() {

    private var message by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val biometricAuthenticator = BiometricAuthenticator(this)

        setContent {
            ComposeBiometricAuthTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val activity = LocalContext.current as FragmentActivity
                    TextButton(
                        onClick = {
                            biometricAuthenticator.promptBiometricAuth(
                                title = "Login",
                                subTitle = "Use your fingerprint",
                                negativeButtonText = "Cancel",
                                fragmentActivity = activity,
                                onSuccess = {
                                    message = "Success"
                                },
                                onError = { _, errorString ->
                                    message = errorString.toString()
                                },
                                onFailed = {
                                    message = "Verification error"
                                }
                            )
                        }
                    ) {
                        Text(text = "Sign in with fingerprint")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = message)
                }
            }
        }
    }
}
