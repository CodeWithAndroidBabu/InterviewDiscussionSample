package com.neomocktest.deep.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

/**
 * @Author: Deep raj
 * @Date: 19/12/23
 */

@Composable
fun AppLoading() {
    Dialog(
        onDismissRequest = {}
    ) {
        CircularProgressIndicator()
    }
}