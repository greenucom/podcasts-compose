package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.size.Scale
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import com.greencom.android.podcasts2.ui.common.rememberImagePainterCustom
import com.greencom.android.podcasts2.ui.screen.discover.previewparameter.TrendingPodcastParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PodcastCard(
    podcast: IPodcast,
    onPodcastClicked: (podcast: IPodcast) -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val cardWidthDp = minOf(PodcastCardUtils.MaxWidthDp, screenWidthDp * 0.8f)

    Card(
        modifier = modifier
            .width(cardWidthDp)
            .aspectRatio(1f),
        onClick = { onPodcastClicked(podcast) },
        shape = MaterialTheme.shapes.large,
        elevation = 2.dp,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainterCustom(podcast.image) {
                    scale(Scale.FILL)
                },
                contentDescription = stringResource(R.string.podcast_cover),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(TrendingPodcastParameterProvider::class)
    podcast: TrendingPodcast
) {
    PodcastsComposeTheme {
        PodcastCard(
            modifier = Modifier.padding(16.dp),
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
    @PreviewParameter(TrendingPodcastParameterProvider::class)
    podcast: TrendingPodcast
) {
    PodcastsComposeTheme {
        PodcastCard(
            modifier = Modifier.padding(16.dp),
            podcast = podcast,
            onPodcastClicked = {},
        )
    }
}
