package com.neomocktest.deep.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.neomocktest.deep.enums.ScreenNavEnum
import com.neomocktest.deep.ui.home.HomeScreen
import com.neomocktest.deep.ui.home.ProfileScreen

/**
 * @Author: Deep raj
 * @Date: 28/12/23
 */

@Composable
fun HomeNavController(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = ScreenNavEnum.HOME.name
    ) {
        composable(route = ScreenNavEnum.HOME.name) {
            HomeScreen()
        }

        composable(route = ScreenNavEnum.PROFILE.name) {
            ProfileScreen()
        }
    }

}