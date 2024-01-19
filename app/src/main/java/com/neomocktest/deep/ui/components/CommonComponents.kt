package com.neomocktest.deep.ui.components

import android.view.ViewTreeObserver
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.compose.AsyncImage
import com.neomocktest.deep.R
import kotlinx.coroutines.launch

/**
 * @Author: Deep raj
 * @Date: 26/12/23
 */

inline fun Modifier.noRippleClickable(
    crossinline onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardWithRound(modifier: Modifier = Modifier, roundRadius: Dp = 16.dp, onClickCallBack:()->Unit, content: @Composable () -> Unit) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(roundRadius),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = {
            onClickCallBack.invoke()
        }) {
        content.invoke()
    }
}

@Composable
fun AnimatedWithVisibility(content: @Composable AnimatedVisibilityScope.() -> Unit){
    AnimatedVisibility(visible = true,
        enter = fadeIn(),
        exit = fadeOut() ) {
        content.invoke(this)
    }
}

@Composable
fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}

@Composable
fun KeyboardStateListener(isKeyboardOpen:(Boolean)->Unit) {
    val view = LocalView.current
    val viewTreeObserver = view.viewTreeObserver
    DisposableEffect(viewTreeObserver) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            isKeyboardOpen.invoke(ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true)
        }
        viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
}

@Composable
fun LoadAsyncImg(
    modifier: Modifier = Modifier,
    load: Any?) {
    AsyncImage(
        model = load ?: R.drawable.ic_profile_image_holder,
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun ExpandableText(
    text: String,
    maxLines: Int = 4, // Change as per your requirement
) {
    var isExpanded by remember { mutableStateOf(false) }
    val seeMoreText = if (isExpanded) "See less" else "See more"

    Column(
        Modifier
            .noRippleClickable {
                isExpanded = !isExpanded
            }
            .animateContentSize()
            .padding(4.dp)) {
        Text(
            text = "$text${if(!isExpanded)"" else seeMoreText}",
            maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth(),
            style = TextStyle(fontSize = 15.sp)
        )
        Text(
            text = if(isExpanded) "" else seeMoreText,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 15.sp)
        )
    }
}


@Composable
fun ImageDrawableButton(
    enabled: Boolean,
    size: DpSize,
    icon: Int,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painterResource(id = icon),
            contentDescription = "null",
            tint = if (enabled) Color.Red else Color.LightGray,
            modifier = Modifier
                .size(size = size)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 18.sp, color = Color.Gray, fontWeight = FontWeight.Normal)
    }
}
@Composable
fun HeartAnimationButton(modifier: Modifier,size: DpSize,icon: Int,text: String,onClick: () ->Unit) {

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color.White, // Change background color as needed
        contentColor = Color.Black
    )

    val interactionSource = remember { MutableInteractionSource() }
    val coroutineScope = rememberCoroutineScope()
    var enabled by remember { mutableStateOf(false) }
    val scale = remember { Animatable(1f) }

    Button(
        onClick = {
            enabled = !enabled
            coroutineScope.launch {
                scale.animateTo(
                    targetValue = 0.8f,
                    animationSpec = tween(100),
                )
                scale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(100),
                )
            }
            onClick.invoke()
        }
        ,
        modifier = modifier.scale(scale.value),
        colors = buttonColors,
        interactionSource = interactionSource) {
        ImageDrawableButton(enabled,size,icon,text)
    }
}







