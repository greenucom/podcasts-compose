package com.greencom.android.podcasts2.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val BluePrimaryLight = Color(0xFF1976D2)
val BluePrimaryDark = Color(0xFF90CAF9)

val SurfaceLight = Color(0xFFFFFFFF)
val SurfaceDark = Color(0xFF222222)

val OnSurfaceUtilLight = Color(0xFFE0E0E0)
val OnSurfaceUtilDark = Color(0xFF3D3D3D)
val Colors.onSurfaceUtil: Color
    get() = if (isLight) OnSurfaceUtilLight else OnSurfaceUtilDark

val SearchButtonBackgroundLight = Color(0xFFF0F0F0)
val SearchButtonBackgroundDark = Color(0xFF373737)
val Colors.searchButtonBackground: Color
    get() = if (isLight) SearchButtonBackgroundLight else SearchButtonBackgroundDark
