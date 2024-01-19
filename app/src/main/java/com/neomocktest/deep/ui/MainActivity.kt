package com.neomocktest.deep.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.neomocktest.deep.test.Repo
import com.neomocktest.deep.test.TestViewModel
import com.neomocktest.deep.theme.NeoMockTestTheme
import com.neomocktest.deep.ui.auth.AuthViewModel
import com.neomocktest.deep.ui.home.lazy_items.ReplyAnswerScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //private val viewModel: AuthViewModel by viewModels()
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeoMockTestTheme {



                ReplyAnswerScreen()
                // A surface container using the 'background' color from the theme
//                Surface(
//                    Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
//                ) {
//                    val navItems = listOf<BottomNavItem>(
//                        BottomNavItem(
//                            "Home", selectedIcon = Icons.Filled.Home,
//                            unSelectedIcon = Icons.Outlined.Home
//                        ),
//                        BottomNavItem(
//                            "Profile", selectedIcon = Icons.Filled.Person,
//                            unSelectedIcon = Icons.Outlined.Person
//                        )
//                    )
//
//                    val selectedIconIndex = rememberSaveable {
//                        mutableIntStateOf(0)
//                    }
//                    navController = rememberNavController()
//
//                    Scaffold(bottomBar = {
//                        NavigationBar {
//                            navItems.forEachIndexed { index, bottomNavItem ->
//                                run {
//                                    NavigationBarItem(
//                                        selected = selectedIconIndex.intValue == index,
//                                        onClick = { selectedIconIndex.intValue = index
//                                            navController.navigate(if(selectedIconIndex.intValue == 0)
//                                                ScreenNavEnum.HOME.name else ScreenNavEnum.PROFILE.name)
//                                                  },
//                                        icon = {
//                                            Icon(
//                                                imageVector = if (selectedIconIndex.intValue == index)
//                                                    bottomNavItem.selectedIcon else bottomNavItem.unSelectedIcon,
//                                                contentDescription = bottomNavItem.itemTitle
//                                            )
//                                        }, label = {
//                                            Text(
//                                                text = bottomNavItem.itemTitle,
//                                                style = TextStyle(
//                                                    fontWeight = if (selectedIconIndex.intValue == index)
//                                                        FontWeight.Bold else FontWeight.Normal))
//                                        })
//                                }
//                            }
//                        }
//                    }, content = { it.toString()
//                        HomeNavController(navController = navController)
//                    })
//                }
            }
        }
    }
}



