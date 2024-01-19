package com.neomocktest.deep.extensions

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

import kotlinx.coroutines.launch

/**
 * @Author: Deep raj
 * @Date: 19/12/23
 */


fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

infix fun IntProgression.step(step: Int): IntProgression {
    return IntProgression.fromClosedRange(first, last, if (this.step > 0) step else -step)
}


fun Modifier.likeReaction() = composed {
    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    var enabled by remember {
        mutableStateOf(false)
    }

    val scale = remember {
        Animatable(1f)
    }

   clickable(interactionSource = interactionSource,indication = null){
    enabled = !enabled
    coroutineScope.launch {
     scale.animateTo(
      0.8f,
      animationSpec = tween(100),
     )
     scale.animateTo(
      1f,
      animationSpec = tween(100),
     )
    }
   }
}


