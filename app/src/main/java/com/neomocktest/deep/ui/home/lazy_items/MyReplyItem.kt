package com.neomocktest.deep.ui.home.lazy_items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neomocktest.deep.theme.skyBlue
import com.neomocktest.deep.ui.components.LoadAsyncImg

/**
 * @Author: Deep raj
 * @Date: 08/01/24
 */

@Preview
@Composable
fun MyReplyItem(userName: String = "", userReply: String = "") {
    Box(
        modifier = Modifier
            .padding(8.dp),
    ) {
        Row (
            modifier = Modifier.fillMaxWidth()
            ,horizontalArrangement = Arrangement.Absolute.Right){
            LoadAsyncImg(
                modifier =
                Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(.2.dp, Color.Black), shape = CircleShape
                    )
                    .padding(2.dp), null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.padding(start = 8.dp, top = 1.dp)) {
                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(skyBlue)
                ) {
                    Text(
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 4.dp,
                            end = 10.dp,
                            bottom = 2.dp
                        ),
                        text = "userName",
                        color = Color.White,
                        fontSize = 15.sp,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 6.dp),
                        text = userReply,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 14.sp,
                        style = TextStyle(fontWeight = FontWeight.Normal)
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Like",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(2.dp)
                        .clickable { }
                )
            }
        }
    }
}