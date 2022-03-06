package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
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
import com.greencom.android.podcasts2.ui.screen.discover.previewparameter.TrendingPodcastsParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private const val KeyRecommendedPodcastList = "recommended_podcast_list"

@OptIn(ExperimentalAnimationApi::class)
fun LazyListScope.recommendedPodcastList(
    state: LazyListState,
    recommendedPodcasts: List<IPodcast>,
    onRecommendedPodcastClicked: (podcast: IPodcast) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    item(key = KeyRecommendedPodcastList) {
        Column(modifier = Modifier.padding(contentPadding)) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.recommended),
                style = MaterialTheme.typography.h4,
            )

            AnimatedContent(recommendedPodcasts.isNotEmpty()) { isNotEmpty ->
                if (isNotEmpty) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(
                            items = recommendedPodcasts,
                            key = { it.id },
                        ) { podcast ->
                            PodcastCard(
                                podcast = podcast,
                                onPodcastClicked = onRecommendedPodcastClicked,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(TrendingPodcastsParameterProvider::class)
    podcasts: List<TrendingPodcast>
) {
    PodcastsComposeTheme {
        Surface {
            val state = rememberLazyListState()
            LazyColumn {
                recommendedPodcastList(
                    state = state,
                    recommendedPodcasts = podcasts,
                    onRecommendedPodcastClicked = {},
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
private fun Dark(
    @PreviewParameter(TrendingPodcastsParameterProvider::class)
    podcasts: List<TrendingPodcast>
) {
    PodcastsComposeTheme {
        Surface {
            val state = rememberLazyListState()
            LazyColumn {
                recommendedPodcastList(
                    state = state,
                    recommendedPodcasts = podcasts,
                    onRecommendedPodcastClicked = {},
                )
            }
        }
    }
}
