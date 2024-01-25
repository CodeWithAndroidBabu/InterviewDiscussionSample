package com.neomocktest.deep.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.neomocktest.deep.enums.ScreenNavEnum
import com.neomocktest.deep.ui.home.HomeScreen
import com.neomocktest.deep.ui.home.pofile.ProfileScreen

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
            HomeScreen(navController)
        }

        composable(route = ScreenNavEnum.PROFILE.name) {
            ProfileScreen()
        }

        composable(route = ScreenNavEnum.COMMENT.name) {

        }
    }
}