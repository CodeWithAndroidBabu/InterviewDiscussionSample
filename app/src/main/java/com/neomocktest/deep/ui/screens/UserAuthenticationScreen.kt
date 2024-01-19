package com.neomocktest.deep.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toOffset
import com.neomocktest.deep.R
import com.neomocktest.deep.data.Users
import com.neomocktest.deep.state.ApiState
import com.neomocktest.deep.theme.appBg
import com.neomocktest.deep.theme.btnColor
import com.neomocktest.deep.ui.auth.AuthViewModel
import com.neomocktest.deep.ui.components.AnimatedWithVisibility
import com.neomocktest.deep.ui.components.AppLoading
import com.neomocktest.deep.ui.components.CardWithRound
import com.neomocktest.deep.ui.components.KeyboardStateListener
import com.neomocktest.deep.ui.components.keyboardAsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * @Author: Deep raj
 * @Date: 26/12/23
 */
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun UserAuthenticationScreen(viewMode: AuthViewModel, msg: (String) -> Unit) {

    val empImg = remember {
        mutableStateOf<Uri?>(null)
    }

    val downloadedImg = remember {
        mutableStateOf("")
    }

    val empId = remember {
        mutableStateOf("")
    }

    val empName = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()


    val currentAuthName = remember {
        mutableStateOf("Sign Up")
    }

    var moved by remember { mutableStateOf(false) }

    KeyboardStateListener {
        moved = it
    }

    val pxToMove = with(LocalDensity.current) {
        -150.dp.toPx().roundToInt()
    }

    val offset by animateIntOffsetAsState(
        targetValue = if (moved && currentAuthName.value == "Sign Up") {
            IntOffset(0, pxToMove)
        } else {
            IntOffset.Zero
        },
        label = "offset"
    )

    val shouldShowDialog = androidx.compose.runtime.remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(appBg)
    ) {

        if (shouldShowDialog.value) {
            AppLoading()
        }

        Column(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                .offset {
                    offset
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_app_icon),
                contentDescription = "",
                modifier = Modifier.padding(24.dp)
            )

            CardWithRound(onClickCallBack = {}) {
                Column {
                    Text(
                        text = currentAuthName.value,
                        modifier = Modifier.padding(start = 24.dp, top = 16.dp),
                        style = TextStyle(fontSize = 20.sp),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))


                    if (currentAuthName.value == "Login")
                        LoginScreen(viewMode = viewMode, msg = msg)
                    else
                        SignUpScreen({ uri ->
                            empImg.value = uri
                        }, { userName ->
                            empName.value = userName
                        }) { userId ->
                            empId.value = userId
                        }
                }
            }

            ClickableText(text = if (currentAuthName.value == "Login")
                AnnotatedString("Don't have an account?")
            else AnnotatedString("Have an account?"),
                modifier = Modifier.padding(8.dp),
                style = TextStyle(fontSize = 18.sp),
                onClick = {
                    currentAuthName.value =
                        if (currentAuthName.value == "Login") "Sign Up" else "Login"
                })

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    scope.launch(Dispatchers.Main) {
                        viewMode.uploadUserImg(empImg.value).collect { downloadImgState ->
                            when (downloadImgState) {
                                is ApiState.Loading -> shouldShowDialog.value = true
                                is ApiState.Success -> {
                                    shouldShowDialog.value = false
                                    downloadedImg.value = downloadImgState.data ?: ""

                                    val user =
                                        Users(
                                            empId = empId.value,
                                            empName.value,
                                            downloadedImg.value
                                        )

                                    viewMode.loginSignUpUser(user).collect { state ->
                                        when (state) {
                                            is ApiState.Loading -> shouldShowDialog.value = true
                                            is ApiState.Success -> {
                                                shouldShowDialog.value = false
                                            }

                                            is ApiState.Error -> {}

                                            is ApiState.Info -> {
                                                shouldShowDialog.value = false
                                                msg.invoke(state.msg)
                                            }

                                            else -> {}
                                        }
                                    }
                                }

                                is ApiState.Error -> {}

                                is ApiState.Info -> {}

                                else -> {}
                            }
                        }
                    }
                }, modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(btnColor),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = currentAuthName.value, color = Color.White, fontSize = 14.sp)
            }
        }
    }
}