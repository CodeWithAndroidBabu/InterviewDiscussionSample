package com.neomocktest.deep.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.neomocktest.deep.R
import com.neomocktest.deep.theme.NeoMockTestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeoMockTestTheme {
                SplashScreen()
            }
        }
    }
}

@Preview
@Composable
fun SplashScreen(){
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(1f).background(Color.Black),
    contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.ic_app_icon), contentDescription = null)
    }
    LaunchedEffect(Unit) {
        delay(1000)
        context.startActivity(Intent(context, MainActivity::class.java))
    }
}
