package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.AsyncImageCustom
import com.greencom.android.podcasts2.ui.common.animatePlaceholderLoadingEffectColor
import com.greencom.android.podcasts2.ui.common.preview.PodcastPreviewParameterProvider
import com.greencom.android.podcasts2.ui.theme.Inter
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.placeholder

private val PodcastCoverSize = 80.dp
private const val TitleMaxLines = 2
private const val AuthorMaxLines = 1
private const val DescriptionMaxLines = 2
private const val CategoryLabelMaxCount = 3

private val TitleTextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.W500,
    fontSize = 18.sp,
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PodcastItem(
    podcast: Podcast,
    onPodcastClicked: (Podcast) -> Unit,
    onSubscribedChanged: (Podcast) -> Unit,
    modifier: Modifier = Modifier,
) {

    Surface(
        modifier = modifier.fillMaxWidth(),
        onClick = { onPodcastClicked(podcast) },
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 12.dp),
        ) {

            Row {
                AsyncImageCustom(
                    modifier = Modifier
                        .size(PodcastCoverSize)
                        .clip(MaterialTheme.shapes.medium),
                    data = podcast.imageUrl,
                    contentDescription = null,
                )

                Column(modifier = Modifier.padding(start = 16.dp)) {

                    Text(
                        text = podcast.title,
                        style = TitleTextStyle,
                        maxLines = TitleMaxLines,
                        overflow = TextOverflow.Ellipsis,
                    )

                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            modifier = Modifier.padding(top = 4.dp),
                            text = podcast.author,
                            style = MaterialTheme.typography.caption,
                            maxLines = AuthorMaxLines,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = podcast.description,
                style = MaterialTheme.typography.body2,
                maxLines = DescriptionMaxLines,
                overflow = TextOverflow.Ellipsis,
            )

            CategoryLabelRow(
                modifier = Modifier.padding(top = 8.dp),
                categories = podcast.categories,
                maxCount = CategoryLabelMaxCount,
            )

            SubscribeButton(
                modifier = Modifier.padding(top = 6.dp),
                isSubscribed = podcast.isSubscribed,
                onSubscribedChanged = {
                    val newPodcast = podcast.copy(isSubscribed = it)
                    onSubscribedChanged(newPodcast)
                },
            )
        }
    }
}

@Composable
fun PodcastItemPlaceholder(
    loadingColor: Color,
    modifier: Modifier = Modifier,
) {

    Surface(modifier = modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row {
                Box(
                    modifier = Modifier
                        .size(PodcastCoverSize)
                        .background(
                            color = loadingColor,
                            shape = MaterialTheme.shapes.medium,
                        )
                )

                Column(modifier = Modifier.padding(start = 16.dp)) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(24.dp)
                            .background(
                                color = loadingColor,
                                shape = MaterialTheme.shapes.placeholder,
                            )
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(0.5f)
                            .height(12.dp)
                            .background(
                                color = loadingColor,
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
                        color = loadingColor,
                        shape = MaterialTheme.shapes.placeholder,
                    )
            )

            Box(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth(0.4f)
                    .height(16.dp)
                    .background(
                        color = loadingColor,
                        shape = MaterialTheme.shapes.placeholder,
                    )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PodcastItemLight(
    @PreviewParameter(PodcastPreviewParameterProvider::class)
    podcast: Podcast
) {
    PodcastsComposeTheme {
        PodcastItem(
            podcast = podcast,
            onPodcastClicked = {},
            onSubscribedChanged = {},
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun PodcastItemDark(
    @PreviewParameter(PodcastPreviewParameterProvider::class)
    podcast: Podcast
) {
    PodcastsComposeTheme {
        PodcastItem(
            podcast = podcast,
            onPodcastClicked = {},
            onSubscribedChanged = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PodcastItemPlaceholderLight() {
    PodcastsComposeTheme {
        Surface {
            val loadingEffectColor by animatePlaceholderLoadingEffectColor()
            PodcastItemPlaceholder(loadingEffectColor)
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun PodcastItemPlaceholderDark() {
    PodcastsComposeTheme {
        Surface {
            val loadingEffectColor by animatePlaceholderLoadingEffectColor()
            PodcastItemPlaceholder(loadingEffectColor)
        }
    }
}
