package com.neomocktest.deep.ui.chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.neomocktest.deep.theme.NeoMockTestTheme
import com.neomocktest.deep.ui.home.lazy_items.ReplyAnswerScreen

class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeoMockTestTheme{
                ReplyAnswerScreen()
            }
        }
    }
}