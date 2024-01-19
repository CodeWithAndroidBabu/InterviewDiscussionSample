package com.neomocktest.deep.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.neomocktest.deep.R
import com.neomocktest.deep.theme.btnColor
import com.neomocktest.deep.ui.auth.AuthViewModel

/**
 * @Author: Deep raj
 * @Date: 19/12/23
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(empImg: (Uri?) -> Unit, empName: (String) -> Unit, empId: (String) -> Unit) {

    val userImg = remember {
        mutableStateOf<Uri?>(null)
    }

    val _empName = remember {
        mutableStateOf("")
    }

    val _empId = remember {
        mutableStateOf("")
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            userImg.value = uri
            empImg.invoke(uri)
        }
    )

    Column(
        modifier = Modifier
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        AsyncImage(
            model = userImg.value ?: R.drawable.ic_profile_image_holder,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
                .clip(CircleShape)
                .padding(8.dp)
                .border(border = BorderStroke(1.dp, Color.Red))
                .clickable {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(modifier = Modifier.fillMaxWidth(1f),
            value = _empName.value, onValueChange = {
                empName.invoke(it)
                _empName.value = it
            }, label = {
                Text(text = "Employee Name")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(modifier = Modifier.fillMaxWidth(1f),
            value = _empId.value, onValueChange = {
                empId.invoke(it)
                _empId.value = it
            }, label = {
                Text(text = "Employee ID")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
    }
}
