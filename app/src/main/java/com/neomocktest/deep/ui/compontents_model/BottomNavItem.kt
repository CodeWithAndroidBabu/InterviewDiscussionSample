package com.neomocktest.deep.ui.compontents_model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @Author: Deep raj
 * @Date: 28/12/23
 */

data class BottomNavItem(
    val itemTitle: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
)
