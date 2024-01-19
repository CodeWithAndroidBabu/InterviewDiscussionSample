package com.neomocktest.deep.ui.home.lazy_items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neomocktest.deep.R
import com.neomocktest.deep.ui.components.ExpandableText
import com.neomocktest.deep.ui.components.HeartAnimationButton
import com.neomocktest.deep.ui.components.ImageDrawableButton
import com.neomocktest.deep.ui.components.LoadAsyncImg


/**
 * @Author: Deep raj
 * @Date: 29/12/23
 */
@Preview
@Composable
fun PostedHomeItem() {

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color.White, // Change background color as needed
        contentColor = Color.Black
    )

    Card(
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(start = 10.dp, top = 8.dp, end = 10.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LoadAsyncImg(
                        modifier =
                        Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .clip(CircleShape)
                            .border(
                                border = BorderStroke(1.dp, Color.Black),
                                shape = CircleShape
                            )
                            .padding(2.dp), null
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "User Name",
                        color = Color.Black,
                        fontSize = 14.sp,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = " - Pune",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        style = TextStyle(fontWeight = FontWeight.Normal)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = " - 1h",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        style = TextStyle(fontWeight = FontWeight.Normal)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                ExpandableText(
                    text = "Lorem ipfasfafafsa fasfasnfjkanfl anlf nalkfnlkan sflkan lkanlf nalkfn alknf lan lka nlan lfkanlkfn alkfasum dolor sit amet, consectetur adipiscing elit. Duis sit amet velit nec enim facilisis ultrices.",
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(8.dp))

                LoadAsyncImg(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(2.dp), load = R.drawable.img_test
                )

                Spacer(modifier = Modifier.height(8.dp))

                Divider(color = Color.LightGray, thickness = 0.5.dp)

                val shouldReactionStart = remember {
                    mutableStateOf(false)
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    HeartAnimationButton(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterVertically),
                        size = DpSize(22.dp, 22.dp),
                        icon = R.drawable.icon_like,
                        text = "Like"
                    ) {

                    }

                    HeartAnimationButton(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterVertically),
                        size = DpSize(22.dp, 22.dp),
                        icon = R.drawable.icon_comment,
                        text = "Comment"
                    ) {
                    }
                }
            }
        }
    }
}