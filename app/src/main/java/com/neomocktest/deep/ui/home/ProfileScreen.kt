package com.neomocktest.deep.ui.home

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.neomocktest.deep.R
import com.neomocktest.deep.theme.NeviBlue
import com.neomocktest.deep.ui.components.CardWithRound
import com.neomocktest.deep.ui.dialogs.YesNoAlertDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Author: Deep raj
 * @Date: 26/12/23
 */


@OptIn(ExperimentalMotionApi::class)
@Composable
@Preview
fun ProfileScreen() {

    val offsetY = remember { Animatable(-1000f) }

    val scope = rememberCoroutineScope()
    var progress by remember {
        mutableFloatStateOf(0f)
    }
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    val shouldDismiss = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        scope.launch {
            var i = 0.0
            val step = 0.1

            while (i <= 1.0) {
                progress = i.toFloat()
                i += step
                delay(60)
                Log.d("nfakfafa", "ss: $i -- $progress")
                if (progress == 1.0f) {
                    Log.d("nfakfafa", "ProfileScreen: $i")
                    offsetY.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(
                            durationMillis = 800,
                            easing = LinearEasing
                        )
                    )
                }
            }
        }
    }

    if (!shouldDismiss.value) {
        YesNoAlertDialog(
            shouldDismiss = shouldDismiss,
            message = "Thank you for choosing NeoMockTest." +
                    " Logging out now? Rest well and return refreshed for more productive days ahead!"
        ) {}
    }

    Column {
        Text(
            text = "Profile",
            fontSize = 24.sp,
            color = Color.White,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .fillMaxWidth(1f)
                .background(NeviBlue)
                .padding(start = 16.dp, top = 4.dp)
        )

        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        ) {
            val properties = motionProperties(id = "profile_pic")

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NeviBlue)
                    .layoutId("container")
            )
            Image(
                painter = painterResource(id = R.drawable.ic_profile_image_holder),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = properties.value.color("background"),
                        shape = CircleShape
                    )
                    .layoutId("profileImg")
            )
            Text(
                text = "Deep Raj",
                fontSize = 24.sp,
                modifier = Modifier.layoutId("userName"),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        CardWithRound(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp)
                .offset {
                    IntOffset(0, offsetY.value.toInt())
                }
                .alpha(progress), roundRadius = 8.dp,
            onClickCallBack = {}) {
            Column(modifier = Modifier.padding(8.dp)) {
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "ID: 7824",
                        fontSize = 18.sp,
                        modifier = Modifier.layoutId("userName")
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(imageVector = Icons.Filled.LocationOn, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Location: Pune, Maharashtra",
                        fontSize = 16.sp,
                        modifier = Modifier.layoutId("userName")
                    )
                }
            }
        }

        CardWithRound(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp)
                .offset {
                    IntOffset(0, offsetY.value.toInt())
                }
                .alpha(progress), roundRadius = 8.dp,
            onClickCallBack = { shouldDismiss.value = false }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(12.dp)
            ) {
                Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = null)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Logout",
                    fontSize = 18.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    color = Color.Red
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = progress,
            onValueChange = {
                progress = it
                println("progress $it")
            },
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}


