package com.greencom.android.podcasts2.ui.screens.discover.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcasts.IPodcast
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast
import com.greencom.android.podcasts2.ui.common.previewparams.TrendingPodcastParameterProvider
import com.greencom.android.podcasts2.ui.common.rememberImagePainterWithCrossfadeAndPlaceholder
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

// TODO: Add elevation to image
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DiscoverPodcastItemPrimary(
    podcast: IPodcast,
    onPodcastClicked: (podcast: IPodcast) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.width(320.dp),
        onClick = { onPodcastClicked(podcast) },
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(modifier = Modifier.padding(bottom = 8.dp)) {
            Image(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp)),
                painter = rememberImagePainterWithCrossfadeAndPlaceholder(podcast.image),
                contentDescription = stringResource(R.string.podcast_cover),
            )

            Spacer(Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = podcast.title,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.W500,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(4.dp))

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = podcast.author,
                    style = MaterialTheme.typography.body2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(TrendingPodcastParameterProvider::class) podcast: TrendingPodcast,
) {
    PodcastsComposeTheme {
        DiscoverPodcastItemPrimary(
            podcast = podcast,
            onPodcastClicked = {},
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
        DiscoverPodcastItemPrimary(
            podcast = podcast,
            onPodcastClicked = {},
        )
    }
}
