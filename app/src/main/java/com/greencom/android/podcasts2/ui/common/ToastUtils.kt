package com.greencom.android.podcasts2.ui.common

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

private const val EmptyString = ""

@Composable
fun rememberToast(): Toast {
    val context = LocalContext.current
    return remember(context) {
        Toast.makeText(context, EmptyString, Toast.LENGTH_SHORT)
    }
}
