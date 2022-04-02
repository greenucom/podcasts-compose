package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.AsyncImageCustom
import com.greencom.android.podcasts2.ui.common.animatePlaceholderLoadingEffectColor
import com.greencom.android.podcasts2.ui.common.preview.PodcastPreviewParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.placeholder

private val CardMaxWidth = 320.dp
private val CardElevation = 6.dp
private const val CardPercentOfScreenWidth = 0.7f
private const val TitleSuffix = " suffixsuffixsuffixsuffixsuffixsuffixsuffixsuffixsuffixsuffix"
private const val TitleMaxLines = 2

val PodcastCardPlaceholderLoadingEffectStartDark = Color(0xFF4C4C4C)
val PodcastCardPlaceholderLoadingEffectEndDark = Color(0xFF585858)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PodcastCard(
    podcast: Podcast,
    onPodcastClicked: (Podcast) -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth = minOf(CardMaxWidth, screenWidth * CardPercentOfScreenWidth)

    Card(
        modifier = modifier.width(cardWidth),
        onClick = { onPodcastClicked(podcast) },
        shape = MaterialTheme.shapes.large,
        elevation = CardElevation,
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            AsyncImageCustom(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                data = podcast.imageUrl,
                contentDescription = null,
            )

            // Append transparent suffix to the title to guarantee that the text will
            // take 2 rows
            val text = buildAnnotatedString {
                append(podcast.title)
                withStyle(SpanStyle(color = Color.Transparent)) {
                    append(TitleSuffix)
                }
            }

            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 16.dp),
                text = text,
                style = MaterialTheme.typography.h6,
                maxLines = TitleMaxLines,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun PodcastCardPlaceholder(
    loadingColor: Color,
    modifier: Modifier = Modifier,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidthDp = minOf(CardMaxWidth, screenWidth * CardPercentOfScreenWidth)

    Card(
        modifier = modifier.width(cardWidthDp),
        shape = MaterialTheme.shapes.large,
        elevation = CardElevation,
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(loadingColor)
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
                            color = loadingColor,
                            shape = MaterialTheme.shapes.placeholder,
                        )
                )

                val text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color.Transparent)) {
                        append(TitleSuffix)
                    }
                }

                Text(
                    text = text,
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
private fun Light(
    @PreviewParameter(PodcastPreviewParameterProvider::class)
    podcast: Podcast
) {
    PodcastsComposeTheme {
        Surface {
            PodcastCard(
                modifier = Modifier.padding(16.dp),
                podcast = podcast,
                onPodcastClicked = {},
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun Dark(
    @PreviewParameter(PodcastPreviewParameterProvider::class)
    podcast: Podcast
) {
    PodcastsComposeTheme {
        Surface {
            PodcastCard(
                modifier = Modifier.padding(16.dp),
                podcast = podcast,
                onPodcastClicked = {},
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PlaceholderLight() {
    PodcastsComposeTheme {
        Surface {
            val color by animatePlaceholderLoadingEffectColor()
            PodcastCardPlaceholder(color)
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun PlaceholderDark() {
    PodcastsComposeTheme {
        Surface {
            val color by animatePlaceholderLoadingEffectColor(
                startColor = PodcastCardPlaceholderLoadingEffectStartDark,
                endColor = PodcastCardPlaceholderLoadingEffectEndDark,
            )
            PodcastCardPlaceholder(color)
        }
    }
}
