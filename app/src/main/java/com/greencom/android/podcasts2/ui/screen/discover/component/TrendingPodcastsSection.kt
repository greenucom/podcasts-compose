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
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.animatePlaceholderLoadingEffectColor
import com.greencom.android.podcasts2.ui.common.component.ErrorMessage
import com.greencom.android.podcasts2.ui.common.component.PodcastItem
import com.greencom.android.podcasts2.ui.common.component.PodcastItemPlaceholder
import com.greencom.android.podcasts2.ui.common.preview.TrendingPodcastSectionParameters
import com.greencom.android.podcasts2.ui.common.preview.TrendingPodcastSectionPreviewParameterProvider
import com.greencom.android.podcasts2.ui.screen.discover.DiscoverViewModel
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private const val KeyHeader = "TrendingPodcastsSectionHeader"
private const val KeyCategorySelector = "TrendingPodcastsSectionCategorySelector"
private const val KeyPlaceholders = "TrendingPodcastsSectionPlaceholders"
private const val KeyError = "TrendingPodcastsSectionError"
private const val PlaceholderCount = 5

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.trendingPodcastsSection(
    selectableCategories: List<SelectableItem<Category>>,
    onCategoryClicked: (Category) -> Unit,
    trendingPodcastsState: DiscoverViewModel.TrendingPodcastsState,
    onPodcastClicked: (Podcast) -> Unit,
    onSubscribedChanged: (Podcast) -> Unit,
    onTryAgainClicked: () -> Unit,
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
            onCategoryClicked = onCategoryClicked,
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 4.dp),
        )
    }

    when (trendingPodcastsState) {
        is DiscoverViewModel.TrendingPodcastsState.Success -> {
            itemsIndexed(
                items = trendingPodcastsState.podcasts,
                key = { _, podcast -> "PodcastItem ${podcast.id}" }
            ) { index, podcast ->

                PodcastItem(
                    podcast = podcast,
                    onPodcastClicked = onPodcastClicked,
                    onSubscribedChanged = onSubscribedChanged,
                )

                if (index != trendingPodcastsState.podcasts.lastIndex) {
                    Divider(color = MaterialTheme.colors.onSurfaceUtil)
                }
            }
        }

        DiscoverViewModel.TrendingPodcastsState.Loading -> {
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

        DiscoverViewModel.TrendingPodcastsState.Error -> {
            item(KeyError) {
                ErrorMessage(
                    modifier = Modifier.padding(top = 8.dp, bottom = 32.dp),
                    onTryAgainClicked = onTryAgainClicked,
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
                DiscoverViewModel.TrendingPodcastsState.Success(parameters.trendingPodcasts)
            LazyColumn {
                trendingPodcastsSection(
                    selectableCategories = parameters.selectableCategories,
                    onCategoryClicked = {},
                    trendingPodcastsState = trendingPodcastsState,
                    onPodcastClicked = {},
                    onSubscribedChanged = {},
                    onTryAgainClicked = {},
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
                DiscoverViewModel.TrendingPodcastsState.Success(parameters.trendingPodcasts)
            LazyColumn {
                trendingPodcastsSection(
                    selectableCategories = parameters.selectableCategories,
                    onCategoryClicked = {},
                    trendingPodcastsState = trendingPodcastsState,
                    onPodcastClicked = {},
                    onSubscribedChanged = {},
                    onTryAgainClicked = {},
                )
            }
        }
    }
}
