package com.greencom.android.podcasts2.ui.screen.discover.component

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.searchBackground

object SearchButtonUtils {

    val MinHeightDp = 40.dp

    private val Elevation = 2.dp

    const val IconAlpha = 0.60f
    const val TextAlpha = 0.74f

    @Composable
    fun colors(): ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.searchBackground,
    )

    @Composable
    fun elevation(): ButtonElevation = ButtonDefaults.elevation(
        defaultElevation = Elevation,
        pressedElevation = Elevation,
        hoveredElevation = Elevation,
        focusedElevation = Elevation,
    )

}
