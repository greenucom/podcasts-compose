package com.greencom.android.podcasts2.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(percent = 50),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp),
)

val Shapes.placeholder: Shape
    get() = RoundedCornerShape(4.dp)
