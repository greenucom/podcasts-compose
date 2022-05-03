package com.greencom.android.podcasts2.ui.screen.podcast.component

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.AsyncImageCustom
import com.greencom.android.podcasts2.ui.common.component.CategoryLabelRow
import com.greencom.android.podcasts2.ui.common.component.SubscribeButton
import com.greencom.android.podcasts2.ui.common.preview.PodcastPreviewParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private const val PodcastCoverSizePercentOfWidth = 0.33f
private const val CategoryMaxCount = 3

@Composable
fun PodcastHeader(
    podcast: Podcast,
    onSubscribedChanged: (Podcast) -> Unit,
    modifier: Modifier = Modifier,
) {

    BoxWithConstraints(modifier = modifier.animateContentSize()) {

        val podcastCoverSize = maxWidth * PodcastCoverSizePercentOfWidth

        Column(
            modifier = Modifier.padding(all = 16.dp),
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
            ) {

                AsyncImageCustom(
                    modifier = Modifier
                        .size(podcastCoverSize)
                        .clip(MaterialTheme.shapes.medium),
                    data = podcast.imageUrl,
                    contentDescription = stringResource(R.string.podcast_cover),
                )

                Spacer(Modifier.weight(1f))

                SubscribeButton(
                    isSubscribed = podcast.isSubscribed,
                    onSubscribedChanged = {
                        val newPodcast = podcast.copy(isSubscribed = it)
                        onSubscribedChanged(newPodcast)
                    },
                )
            }

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = podcast.title,
                style = MaterialTheme.typography.h6,
            )

            CategoryLabelRow(
                modifier = Modifier.padding(top = 4.dp),
                categories = podcast.categories,
                maxCount = CategoryMaxCount,
            )

            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = podcast.description,
                style = MaterialTheme.typography.body2,
            )
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
            PodcastHeader(
                podcast = podcast,
                onSubscribedChanged = {},
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
            PodcastHeader(
                podcast = podcast,
                onSubscribedChanged = {},
            )
        }
    }
}
