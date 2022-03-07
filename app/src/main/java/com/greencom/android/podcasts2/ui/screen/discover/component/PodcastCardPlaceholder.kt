package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.common.PlaceholderLoadingEffect
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.placeholder

private const val PlaceholderText = ""

val PodcastCardPlaceholderLoadingStartDark = Color(0xFF4C4C4C)
val PodcastCardPlaceholderLoadingEndDark = Color(0xFF585858)

@Composable
fun PodcastCardPlaceholder(
    color: Color,
    modifier: Modifier = Modifier,
) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val cardWidthDp = minOf(
        PodcastCardUtils.MaxWidthDp,
        screenWidthDp * PodcastCardUtils.MaxWidthPercentOfScreen,
    )

    Card(
        modifier = modifier.width(cardWidthDp),
        shape = MaterialTheme.shapes.large,
        elevation = 6.dp,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(color)
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth(0.7f)
                        .height(20.dp)
                        .background(
                            color = color,
                            shape = MaterialTheme.shapes.placeholder,
                        )
                )

                Text(
                    text = PodcastCardUtils.handlePodcastTitle(PlaceholderText),
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme{
        Surface {
            PlaceholderLoadingEffect {
                PodcastCardPlaceholder(
                    modifier = Modifier.padding(16.dp),
                    color = it,
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun Dark() {
    PodcastsComposeTheme {
        Surface {
            PlaceholderLoadingEffect(
                startColor = MaterialTheme.colors.onSurface.copy(alpha = 0.075f),
                endColor = MaterialTheme.colors.onSurface.copy(alpha = 0.125f),
            ) {
                PodcastCardPlaceholder(
                    modifier = Modifier.padding(16.dp),
                    color = it,
                )
            }
        }
    }
}
