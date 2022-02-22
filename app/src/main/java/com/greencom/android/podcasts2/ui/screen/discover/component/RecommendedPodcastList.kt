package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import com.greencom.android.podcasts2.ui.common.previewparameter.TrendingPodcastsParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun RecommendedPodcastList(
    podcasts: List<IPodcast>,
    onPodcastClicked: (podcast: IPodcast) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.recommended),
            style = MaterialTheme.typography.h4,
        )

        Spacer(Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = podcasts,
                key = { it.id },
            ) { podcast ->
                PodcastCard(
                    podcast = podcast,
                    onPodcastClicked = onPodcastClicked,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(TrendingPodcastsParameterProvider::class) podcasts: List<TrendingPodcast>,
) {
    PodcastsComposeTheme {
        Surface {
            RecommendedPodcastList(
                podcasts = podcasts,
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
    @PreviewParameter(TrendingPodcastsParameterProvider::class) podcasts: List<TrendingPodcast>,
) {
    PodcastsComposeTheme {
        Surface {
            RecommendedPodcastList(
                podcasts = podcasts,
                onPodcastClicked = {},
            )
        }
    }
}
