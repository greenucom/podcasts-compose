package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
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
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.animatePlaceholderLoadingEffectColor
import com.greencom.android.podcasts2.ui.common.component.ErrorMessage
import com.greencom.android.podcasts2.ui.common.component.PodcastItem
import com.greencom.android.podcasts2.ui.common.component.PodcastItemPlaceholder
import com.greencom.android.podcasts2.ui.common.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.common.preview.TrendingPodcastSectionParameters
import com.greencom.android.podcasts2.ui.common.preview.TrendingPodcastSectionPreviewParameterProvider
import com.greencom.android.podcasts2.ui.screen.discover.DiscoverTrendingPodcastsState
import com.greencom.android.podcasts2.ui.screen.discover.DiscoverViewEvent
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private const val KeyHeader = "TrendingPodcastsSectionHeader"
private const val KeyCategorySelector = "TrendingPodcastsSectionCategorySelector"
private const val KeyPlaceholders = "TrendingPodcastsSectionPlaceholders"
private const val KeyError = "TrendingPodcastsSectionError"
private const val PlaceholderCount = 5

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.trendingPodcastsSection(
    dispatchEvent: (DiscoverViewEvent) -> Unit,
    selectableCategories: List<SelectableItem<CategoryUiModel>>,
    trendingPodcastsState: DiscoverTrendingPodcastsState,
) {
    item(KeyHeader) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 4.dp),
            text = stringResource(R.string.trending),
            style = MaterialTheme.typography.h4,
        )
    }

    stickyHeader(KeyCategorySelector) {
        TrendingCategorySelector(
            selectableCategories = selectableCategories,
            onCategoryClicked = {
                val event = DiscoverViewEvent.ToggleSelectableCategory(it)
                dispatchEvent(event)
            },
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 2.dp),
        )
    }

    when (trendingPodcastsState) {
        is DiscoverTrendingPodcastsState.Success -> {
            itemsIndexed(
                items = trendingPodcastsState.podcasts,
                key = { _, podcast -> "PodcastItem ${podcast.id}" }
            ) { index, podcast ->

                PodcastItem(
                    podcast = podcast,
                    onPodcastClicked = {
                        val event = DiscoverViewEvent.ShowPodcastScreen(it)
                        dispatchEvent(event)
                    },
                    onSubscribedChanged = {
                        val event = DiscoverViewEvent.ChangeSubscription(it)
                        dispatchEvent(event)
                    },
                )

                if (index != trendingPodcastsState.podcasts.lastIndex) {
                    Divider(color = MaterialTheme.colors.onSurfaceUtil)
                }
            }
        }

        DiscoverTrendingPodcastsState.Loading -> {
            item(KeyPlaceholders) {
                val placeholderLoadingColor by animatePlaceholderLoadingEffectColor()

                Column {
                    repeat(PlaceholderCount) { index ->
                        PodcastItemPlaceholder(loadingColor = placeholderLoadingColor)

                        if (index != PlaceholderCount - 1) {
                            Divider(color = MaterialTheme.colors.onSurfaceUtil)
                        }
                    }
                }
            }
        }

        DiscoverTrendingPodcastsState.Error -> {
            item(KeyError) {
                ErrorMessage(
                    modifier = Modifier.padding(top = 8.dp, bottom = 32.dp),
                    onTryAgainClicked = {
                        val event = DiscoverViewEvent.RefreshTrendingPodcasts
                        dispatchEvent(event)
                    },
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(TrendingPodcastSectionPreviewParameterProvider::class)
    parameters: TrendingPodcastSectionParameters
) {
    PodcastsComposeTheme {
        Surface {
            val trendingPodcastsState =
                DiscoverTrendingPodcastsState.Success(parameters.trendingPodcasts)
            LazyColumn {
                trendingPodcastsSection(
                    dispatchEvent = {},
                    selectableCategories = parameters.selectableCategories,
                    trendingPodcastsState = trendingPodcastsState,
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
    @PreviewParameter(TrendingPodcastSectionPreviewParameterProvider::class)
    parameters: TrendingPodcastSectionParameters
) {
    PodcastsComposeTheme {
        Surface {
            val trendingPodcastsState =
                DiscoverTrendingPodcastsState.Success(parameters.trendingPodcasts)
            LazyColumn {
                trendingPodcastsSection(
                    dispatchEvent = {},
                    selectableCategories = parameters.selectableCategories,
                    trendingPodcastsState = trendingPodcastsState,
                )
            }
        }
    }
}
