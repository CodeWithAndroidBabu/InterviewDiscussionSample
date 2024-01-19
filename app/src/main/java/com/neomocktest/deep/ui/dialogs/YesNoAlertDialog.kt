package com.neomocktest.deep.ui.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@Composable
fun YesNoAlertDialog(
    title: String = "Alert",
    message: String,
    shouldDismiss: MutableState<Boolean>,
    onConfirm: () -> Unit,
) {

    if (shouldDismiss.value) return

    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = { shouldDismiss.value = true },
        title = {
            Text(text = title, fontSize = 18.sp, color = Color.Black)
        },
        text = {
            Text(
                text = message,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                    shouldDismiss.value = true
                }
            ) {
                Text(text = "Yes", color = Color.Red)
            }
        },
        dismissButton = {
            TextButton(
                onClick = { shouldDismiss.value = true }
            ) {
                Text(text = "Cancel", color = Color.Black)
            }
        },
        shape = RoundedCornerShape(12.dp),
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
    )
}