package com.neomocktest.deep.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neomocktest.deep.R
import com.neomocktest.deep.data.Users
import com.neomocktest.deep.state.ApiState
import com.neomocktest.deep.theme.appBg
import com.neomocktest.deep.theme.btnColor
import com.neomocktest.deep.ui.auth.AuthViewModel
import com.neomocktest.deep.ui.components.AppLoading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @Author: Deep raj
 * @Date: 01/11/23
 */

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun LoginScreen(viewMode: AuthViewModel, msg: (String) -> Unit) {
    val empId = remember {
        mutableStateOf("")
    }



    Column(
        modifier = Modifier
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(modifier = Modifier.fillMaxWidth(1f),
            value = empId.value, onValueChange = {
                empId.value = it

            }, label = {
                Text(text = "Employee ID")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

//            Button(
//                onClick = {
//                    val user = Users(empId = empId.value,empName.value,"")
//                    scope.launch(Dispatchers.Main) {
//                        viewMode.userLogin(user).collect{state->
//                            when(state){
//                                is ApiState.Loading-> shouldShowDialog.value = true
//                                is ApiState.Success->{
//                                    shouldShowDialog.value = false
//                                    msg.invoke("User has been created")
//                                }
//
//                                is ApiState.Error->{}
//
//                                is ApiState.Info -> {
//                                    shouldShowDialog.value = false
//                                    msg.invoke(state.msg)
//                                }
//
//                                else -> {}
//                            }
//                        }
//                    }
//                }, modifier = Modifier
//                    .fillMaxWidth(1f)
//                    .height(45.dp),
//                colors = ButtonDefaults.buttonColors(btnColor),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Text(text = "Login", color = Color.White, fontSize = 14.sp)
//            }
    }

}