package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.common.PlaceholderLoadingEffect
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.placeholder

@Composable
fun PodcastItemPlaceholder(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Box(
                    modifier = Modifier
                        .size(PodcastItemUtils.ImageSizeDp)
                        .background(
                            color = color,
                            shape = MaterialTheme.shapes.medium,
                        )
                )

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(24.dp)
                            .background(
                                color = color,
                                shape = MaterialTheme.shapes.placeholder,
                            )
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(0.5f)
                            .height(12.dp)
                            .background(
                                color = color,
                                shape = MaterialTheme.shapes.placeholder,
                            )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(
                        color = color,
                        shape = MaterialTheme.shapes.placeholder,
                    )
            )

            Box(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth(0.4f)
                    .height(16.dp)
                    .background(
                        color = color,
                        shape = MaterialTheme.shapes.placeholder,
                    )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            PlaceholderLoadingEffect {
                PodcastItemPlaceholder(it)
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
            PlaceholderLoadingEffect {
                PodcastItemPlaceholder(it)
            }
        }
    }
}
