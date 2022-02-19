package com.greencom.android.podcasts2.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.greencom.android.podcasts2.R

val Inter = FontFamily(
    Font(R.font.inter_thin, FontWeight.W100),
    Font(R.font.inter_extra_light, FontWeight.W200),
    Font(R.font.inter_light, FontWeight.W300),
    Font(R.font.inter_regular, FontWeight.W400),
    Font(R.font.inter_medium, FontWeight.W500),
    Font(R.font.inter_semi_bold, FontWeight.W600),
    Font(R.font.inter_bold, FontWeight.W700),
    Font(R.font.inter_extra_bold, FontWeight.W800),
    Font(R.font.inter_black, FontWeight.W900),
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W600,
        fontSize = 96.sp,
    ),
    h2 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W600,
        fontSize = 60.sp,
    ),
    h3 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W600,
        fontSize = 48.sp,
    ),
    h4 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W600,
        fontSize = 34.sp,
    ),
    h5 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
    ),
    h6 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
    ),
    body1 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),
    body2 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    button = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
    ),
    caption = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
    ),
    overline = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W400,
        fontSize = 10.sp,
    ),
)
