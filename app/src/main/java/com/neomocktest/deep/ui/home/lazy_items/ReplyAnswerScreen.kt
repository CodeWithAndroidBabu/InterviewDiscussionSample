package com.neomocktest.deep.ui.home.lazy_items


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neomocktest.deep.theme.lightGrey
import com.neomocktest.deep.theme.skyBlue

/**
 * @Author: Deep raj
 * @Date: 29/12/23
 */

data class Questions(val name: String, val reply: String,val type:String="")


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyAnswerScreen() {
    val list = remember {
        mutableStateListOf<Questions>()
    }

    list.add(Questions("Deep", "hajfhajfafafas fasfasfsa "))
    list.add(Questions("Raj", "12131313 fasfasfsa",))

    list.add(Questions("Rahul", "ffsssfs fasfasfsa "))
    list.add(Questions("Dinesh", "576562435 fasfasfsa "))
    list.add(Questions("Parmar", "dvfddfdfd fasfasfsa "))
    list.add(Questions("Yogi", "mjkhhgwwds fasfasfsa "))
    list.add(Questions("Modi", "fgfjuuuuuuuuuu fasfasfsa "))
    list.add(Questions("Shivraj", "ljkjijk fasfasfsa "))
    list.add(Questions("Shiva", "fsafafaf fasfasfsa "))
    list.add(Questions("Ram", "fanjnjxc  "))
    list.add(Questions("Deep", "hajfhajfafafas fasfasfsa "))
    list.add(Questions("Raj", "12131313 fasfasfsa "))


    var text by rememberSaveable { mutableStateOf("Text") }
    Column(Modifier.fillMaxSize()) {
        val state = rememberLazyListState()
        LazyColumn(modifier = Modifier
            .weight(1f)
            .padding(4.dp), content = {
            items(list) {
                if(it.type == "reply"){
                    MyReplyItem(it.name,it.reply)
                }else{
                    OthersQuestionReplyItem(userName = it.name, userReply = it.reply)
                }
            }
        })
        ChatInputField(list)
        Spacer(modifier = Modifier.height(4.dp))
    }

}



@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ChatInputField(list: MutableList<Questions>) {
    var message by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Message input field
        TextField(
            maxLines = 1,
            textStyle = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .padding(start = 20.dp, end = 10.dp)
                .weight(1f)
                .background(lightGrey, RoundedCornerShape(100.dp)),
            shape = RoundedCornerShape(100.dp),
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Transparent,
                containerColor = lightGrey,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            label = {
                Text(text = "Reply here...")
            },
            value = message,
            onValueChange = {
                message = it
            }
        )
        Icon(
            imageVector = Icons.Default.Send,
            tint = skyBlue,
            contentDescription = "Send",
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false)
                ) {
                    if (message.isNotBlank()) {
                        // onSendMessage(message)
                        message = ""
                        list.add(
                            Questions(
                                "Deep",
                                "New Data Added "
                            )
                        )

                    }
                }
                .padding(8.dp)
                .size(30.dp)

        )
        Spacer(modifier = Modifier.width(10.dp))
    }
}
