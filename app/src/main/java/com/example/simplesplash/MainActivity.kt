package com.example.simplesplash

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplesplash.ui.theme.SimpleSplashTheme

class MainActivity : ComponentActivity() {
    private val splashScreenViewModel by lazy {
        ViewModelProvider(this)[SimpleSplashViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { splashScreenViewModel.isSplashScreenVisible.value } //mantener splash screen hasta que sea false
            setOnExitAnimationListener { splash ->
                val rotateAnimator = ValueAnimator.ofFloat(0f, 90f)
                rotateAnimator.addUpdateListener {
                    splash.iconView.rotation = it.animatedValue as Float
                }
                rotateAnimator.doOnEnd { splash.remove() }
                rotateAnimator.start()
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleSplashTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleSplashTheme {
        Greeting("Android")
    }
}