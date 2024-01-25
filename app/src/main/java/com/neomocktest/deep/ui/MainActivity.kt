package com.neomocktest.deep.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.neomocktest.deep.enums.ScreenNavEnum
import com.neomocktest.deep.theme.NeoMockTestTheme
import com.neomocktest.deep.ui.compontents_model.BottomNavItem
import com.neomocktest.deep.ui.nav.HomeNavController
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //private val viewModel: AuthViewModel by viewModels()
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeoMockTestTheme {


                // ReplyAnswerScreen()
                //  A surface container using the 'background' color from the theme
                Surface(
                    Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navItems = listOf<BottomNavItem>(
                        BottomNavItem(
                            "Home", selectedIcon = Icons.Filled.Home,
                            unSelectedIcon = Icons.Outlined.Home
                        ),
                        BottomNavItem(
                            "Profile", selectedIcon = Icons.Filled.Person,
                            unSelectedIcon = Icons.Outlined.Person
                        )
                    )

                    val selectedIconIndex = rememberSaveable {
                        mutableIntStateOf(0)
                    }
                    navController = rememberNavController()

                    Scaffold(bottomBar = {
                        NavigationBar {
                            navItems.forEachIndexed { index, bottomNavItem ->
                                run {
                                    NavigationBarItem(
                                        selected = selectedIconIndex.intValue == index,
                                        onClick = {
                                            selectedIconIndex.intValue = index
                                            navController.navigate(
                                                if (selectedIconIndex.intValue == 0)
                                                    ScreenNavEnum.HOME.name else ScreenNavEnum.PROFILE.name
                                            )
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if (selectedIconIndex.intValue == index)
                                                    bottomNavItem.selectedIcon else bottomNavItem.unSelectedIcon,
                                                contentDescription = bottomNavItem.itemTitle
                                            )
                                        }, label = {
                                            Text(
                                                text = bottomNavItem.itemTitle,
                                                style = TextStyle(
                                                    fontWeight = if (selectedIconIndex.intValue == index)
                                                        FontWeight.Bold else FontWeight.Normal
                                                )
                                            )
                                        })
                                }
                            }
                        }
                    }, content = {
                        it.toString()
                        HomeNavController(navController = navController)
                    })
                }
            }
        }
    }
}



