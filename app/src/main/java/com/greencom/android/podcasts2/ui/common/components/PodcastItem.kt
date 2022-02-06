package com.greencom.android.podcasts2.ui.common.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcasts.IPodcast
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast
import com.greencom.android.podcasts2.ui.common.rememberImagePainterWithCrossfadeAndPlaceholder
import com.greencom.android.podcasts2.ui.previewparams.TrendingPodcastParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PodcastItem(
    modifier: Modifier = Modifier,
    podcast: IPodcast,
    onClick: (podcast: IPodcast) -> Unit,
    onSubscribeClicked: (isSubscribed: Boolean) -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        onClick = { onClick(podcast) },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Image(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    painter = rememberImagePainterWithCrossfadeAndPlaceholder(podcast.image),
                    contentDescription = stringResource(R.string.podcast_cover),
                )

                Spacer(Modifier.width(16.dp))

                Column {
                    Text(
                        text = podcast.title,
                        style = MaterialTheme.typography.h6,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = podcast.author,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = podcast.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(8.dp))

            SubscribeButton(
                isSubscribed = podcast.isSubscribed,
                onClick = onSubscribeClicked,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(TrendingPodcastParameterProvider::class) podcast: TrendingPodcast,
) {
    PodcastsComposeTheme {
        PodcastItem(
            podcast = podcast,
            onClick = {},
            onSubscribeClicked = {},
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun Dark(
    @PreviewParameter(TrendingPodcastParameterProvider::class) podcast: TrendingPodcast,
) {
    PodcastsComposeTheme {
        PodcastItem(
            podcast = podcast,
            onClick = {},
            onSubscribeClicked = {},
        )
    }
}
