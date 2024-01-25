package com.neomocktest.deep.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.neomocktest.deep.ui.home.lazy_items.PostedHomeItem

/**
 * @Author: Deep raj
 * @Date: 28/12/23
 */

@Composable
fun HomeScreen(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(content = {
                items(10) {
                    PostedHomeItem(navController)
                }
        })
    }

}