package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.animatePlaceholderLoadingEffectColor
import com.greencom.android.podcasts2.ui.common.model.podcast.PodcastUiModel
import com.greencom.android.podcasts2.ui.common.placeholderLoadingEffectEnd
import com.greencom.android.podcasts2.ui.common.placeholderLoadingEffectStart
import com.greencom.android.podcasts2.ui.common.preview.PodcastListPreviewParameterProvider
import com.greencom.android.podcasts2.ui.screen.discover.DiscoverRecommendedPodcastsState
import com.greencom.android.podcasts2.ui.screen.discover.DiscoverViewEvent
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private const val KeyRecommendedPodcastsSection = "RecommendedPodcastsSection"
private const val PlaceholderCount = 4

private val Colors.podcastCardPlaceholderLoadingEffectStart: Color
    get() = if (isLight) placeholderLoadingEffectStart else PodcastCardPlaceholderLoadingEffectStartDark
private val Colors.podcastCardPlaceholderLoadingEffectEnd: Color
    get() = if (isLight) placeholderLoadingEffectEnd else PodcastCardPlaceholderLoadingEffectEndDark

fun LazyListScope.recommendedPodcastsSection(
    dispatchEvent: (DiscoverViewEvent) -> Unit,
    innerLazyListState: LazyListState,
    recommendedPodcastsState: DiscoverRecommendedPodcastsState,
    modifier: Modifier = Modifier,
) {
    item(KeyRecommendedPodcastsSection) {

        Column(modifier = modifier) {

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.recommended),
                style = MaterialTheme.typography.h4,
            )

            val placeholderLoadingColor = if (
                recommendedPodcastsState is DiscoverRecommendedPodcastsState.Loading
            ) {
                val placeholderLoadingColorStart =
                    MaterialTheme.colors.podcastCardPlaceholderLoadingEffectStart
                val placeholderLoadingColorEnd =
                    MaterialTheme.colors.podcastCardPlaceholderLoadingEffectEnd
                animatePlaceholderLoadingEffectColor(
                    startColor = placeholderLoadingColorStart,
                    endColor = placeholderLoadingColorEnd,
                ).value
            } else {
                Color.Transparent
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                state = innerLazyListState,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                when (recommendedPodcastsState) {

                    is DiscoverRecommendedPodcastsState.Success -> {
                        items(
                            items = recommendedPodcastsState.podcasts,
                            key = { "PodcastCard ${it.id}" },
                        ) { podcast ->
                            PodcastCard(
                                podcast = podcast,
                                onPodcastClicked = {
                                    val event = DiscoverViewEvent.ShowPodcastScreen(it)
                                    dispatchEvent(event)
                                },
                            )
                        }
                    }

                    DiscoverRecommendedPodcastsState.Loading -> {
                        items(
                            count = PlaceholderCount,
                            key = { "PodcastCardPlaceholder $it" },
                        ) {
                            PodcastCardPlaceholder(loadingColor = placeholderLoadingColor)
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
    @PreviewParameter(PodcastListPreviewParameterProvider::class)
    podcasts: List<PodcastUiModel>
) {
    PodcastsComposeTheme {
        Surface {
            val innerLazyListState = rememberLazyListState()
            val recommendedPodcastsState = DiscoverRecommendedPodcastsState.Success(podcasts)
            LazyColumn {
                recommendedPodcastsSection(
                    dispatchEvent = {},
                    innerLazyListState = innerLazyListState,
                    recommendedPodcastsState = recommendedPodcastsState,
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
    @PreviewParameter(PodcastListPreviewParameterProvider::class)
    podcasts: List<PodcastUiModel>
) {
    PodcastsComposeTheme {
        Surface {
            val innerLazyListState = rememberLazyListState()
            val recommendedPodcastsState = DiscoverRecommendedPodcastsState.Success(podcasts)
            LazyColumn {
                recommendedPodcastsSection(
                    dispatchEvent = {},
                    innerLazyListState = innerLazyListState,
                    recommendedPodcastsState = recommendedPodcastsState,
                )
            }
        }
    }
}
