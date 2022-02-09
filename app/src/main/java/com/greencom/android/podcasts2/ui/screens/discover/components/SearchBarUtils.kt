package com.greencom.android.podcasts2.ui.screens.discover.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

object SearchBarUtils {
    val Height = 64.dp
    const val CornerPercent = 50

    @Composable
    fun obtainTextFieldColors(isDarkTheme: Boolean): TextFieldColors {
        val borderColor = MaterialTheme.colors.onSurfaceUtil
        return if (isDarkTheme) {
            TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = borderColor,
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                disabledBorderColor = borderColor,
                errorBorderColor = borderColor,
            )
        } else {
            TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                disabledBorderColor = borderColor,
                errorBorderColor = borderColor,
            )
        }
    }

}
