package com.greencom.android.podcasts2.ui.screens.discover.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.size.Scale
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcasts.IPodcast
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast
import com.greencom.android.podcasts2.ui.common.previewparams.TrendingPodcastParameterProvider
import com.greencom.android.podcasts2.ui.common.rememberImagePainterWithCrossfadeAndPlaceholder
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PodcastCard(
    podcast: IPodcast,
    onPodcastClick: (podcast: IPodcast) -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val cardWidthDp = minOf(320.dp, screenWidthDp * 0.8f)

    Card(
        modifier = modifier
            .width(cardWidthDp)
            .aspectRatio(1f),
        onClick = { onPodcastClick(podcast) },
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainterWithCrossfadeAndPlaceholder(podcast.image) {
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
    @PreviewParameter(TrendingPodcastParameterProvider::class) podcast: TrendingPodcast,
) {
    PodcastsComposeTheme {
        PodcastCard(
            modifier = Modifier.padding(16.dp),
            podcast = podcast,
            onPodcastClick = {},
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
        PodcastCard(
            modifier = Modifier.padding(16.dp),
            podcast = podcast,
            onPodcastClick = {},
        )
    }
}
