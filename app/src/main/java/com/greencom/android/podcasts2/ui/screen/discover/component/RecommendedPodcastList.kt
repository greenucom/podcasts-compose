package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import com.greencom.android.podcasts2.ui.common.rememberPlaceholderLoadingColor
import com.greencom.android.podcasts2.ui.screen.discover.DiscoverViewModel
import com.greencom.android.podcasts2.ui.screen.discover.previewparameter.TrendingPodcastsParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private const val KeyRecommendedPodcastList = "recommended_podcast_list"

private const val PlaceholderCount = 4

@OptIn(ExperimentalAnimationApi::class)
fun LazyListScope.recommendedPodcastList(
    lazyRowState: LazyListState,
    recommendedPodcastsState: DiscoverViewModel.RecommendedPodcastsState,
    onRecommendedPodcastClicked: (podcast: IPodcast) -> Unit,
    modifier: Modifier = Modifier,
) {
    item(key = KeyRecommendedPodcastList) {

        Column(modifier = modifier) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.recommended),
                style = MaterialTheme.typography.h4,
            )

            val placeholderColor by rememberPlaceholderLoadingColor()

            LazyRow(
                modifier = modifier.fillMaxWidth(),
                state = lazyRowState,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                when (recommendedPodcastsState) {
                    is DiscoverViewModel.RecommendedPodcastsState.Success -> {
                        items(
                            items = recommendedPodcastsState.recommendedPodcasts,
                            key = { it.id },
                        ) { podcast ->
                            PodcastCard(
                                podcast = podcast,
                                onPodcastClicked = onRecommendedPodcastClicked,
                            )
                        }
                    }

                    DiscoverViewModel.RecommendedPodcastsState.Loading -> {
                        items(count = PlaceholderCount) {
                            PodcastCardPlaceholder(placeholderColor)
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
                    lazyRowState = state,
                    recommendedPodcastsState = DiscoverViewModel.RecommendedPodcastsState.Success(podcasts),
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
                    lazyRowState = state,
                    recommendedPodcastsState = DiscoverViewModel.RecommendedPodcastsState.Success(podcasts),
                    onRecommendedPodcastClicked = {},
                )
            }
        }
    }
}
