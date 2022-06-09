package com.greencom.android.podcasts2.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.greencom.android.podcasts2.R

val InterFontFamily = FontFamily(
    Font(resId = R.font.inter_thin, weight = FontWeight.W100),
    Font(resId = R.font.inter_extra_light, weight = FontWeight.W200),
    Font(resId = R.font.inter_light, weight = FontWeight.W300),
    Font(resId = R.font.inter_regular, weight = FontWeight.W400),
    Font(resId = R.font.inter_medium, weight = FontWeight.W500),
    Font(resId = R.font.inter_semi_bold, weight = FontWeight.W600),
    Font(resId = R.font.inter_bold, weight = FontWeight.W700),
    Font(resId = R.font.inter_extra_bold, weight = FontWeight.W800),
    Font(resId = R.font.inter_black, weight = FontWeight.W900),
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 42.sp,
    ),
    h2 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 36.sp,
    ),
    h3 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 30.sp,
    ),
    h4 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 26.sp,
    ),
    h5 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 22.sp,
    ),
    h6 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
    ),
    body1 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),
    body2 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    button = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
    ),
    caption = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
    ),
    overline = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 10.sp,
    )
)

@Composable
@Preview
fun TypographyPreview() {
    PodcastsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Headline 1", style = MaterialTheme.typography.h1)
                Text(text = "Headline 2", style = MaterialTheme.typography.h2)
                Text(text = "Headline 3", style = MaterialTheme.typography.h3)
                Text(text = "Headline 4", style = MaterialTheme.typography.h4)
                Text(text = "Headline 5", style = MaterialTheme.typography.h5)
                Text(text = "Headline 6", style = MaterialTheme.typography.h6)
                Text(text = "Subtitle 1", style = MaterialTheme.typography.subtitle1)
                Text(text = "Subtitle 2", style = MaterialTheme.typography.subtitle2)
                Text(text = "Body 1", style = MaterialTheme.typography.body1)
                Text(text = "Body 2", style = MaterialTheme.typography.body2)
                Text(text = "Button", style = MaterialTheme.typography.button)
                Text(text = "Caption", style = MaterialTheme.typography.caption)
                Text(text = "Overline", style = MaterialTheme.typography.overline)
            }
        }
    }
}
