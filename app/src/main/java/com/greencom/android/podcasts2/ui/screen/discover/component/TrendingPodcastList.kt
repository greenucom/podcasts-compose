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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import com.greencom.android.podcasts2.ui.common.PlaceholderLoadingEffect
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.component.ErrorMessage
import com.greencom.android.podcasts2.ui.common.component.PodcastItem
import com.greencom.android.podcasts2.ui.common.component.PodcastItemPlaceholder
import com.greencom.android.podcasts2.ui.screen.discover.DiscoverViewModel
import com.greencom.android.podcasts2.ui.screen.discover.previewparameter.TrendingPodcastListParameter
import com.greencom.android.podcasts2.ui.screen.discover.previewparameter.TrendingPodcastListParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private const val KeyHeader = "trending_podcast_list_header"
private const val KeyCategorySelector = "trending_podcast_list_category_selector"
private const val KeyLoading = "trending_podcast_list_loading"
private const val KeyError = "trending_podcast_list_error"

private const val PlaceholderCount = 4

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.trendingPodcastList(
    selectableCategories: List<SelectableItem<Category>>,
    onSelectableCategoryClicked: (selectableCategory: SelectableItem<Category>) -> Unit,
    trendingPodcastsState: DiscoverViewModel.TrendingPodcastsState,
    onTrendingPodcastClicked: (podcast: IPodcast) -> Unit,
    onTryAgainClicked: () -> Unit,
) {
    item(key = KeyHeader) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 4.dp),
            text = stringResource(R.string.trending),
            style = MaterialTheme.typography.h4,
        )
    }

    stickyHeader(key = KeyCategorySelector) {
        TrendingCategorySelector(
            selectableCategories = selectableCategories,
            onSelectableCategoryClicked = onSelectableCategoryClicked,
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 4.dp)
        )
    }

    when (trendingPodcastsState) {
        is DiscoverViewModel.TrendingPodcastsState.Success -> {
            itemsIndexed(
                items = trendingPodcastsState.trendingPodcasts,
                key = { _, podcast -> podcast.id },
            ) { index, podcast ->
                PodcastItem(
                    podcast = podcast,
                    onPodcastClicked = onTrendingPodcastClicked,
                )

                if (index != trendingPodcastsState.trendingPodcasts.lastIndex) {
                    Divider(color = MaterialTheme.colors.onSurfaceUtil)
                }
            }
        }

        DiscoverViewModel.TrendingPodcastsState.Loading -> {
            item(key = KeyLoading) {
                PlaceholderLoadingEffect { color ->
                    Column {
                        repeat(PlaceholderCount) { index ->
                            PodcastItemPlaceholder(color)

                            if (index < PlaceholderCount - 1) {
                                Divider(color = MaterialTheme.colors.onSurfaceUtil)
                            }
                        }
                    }
                }
            }
        }

        DiscoverViewModel.TrendingPodcastsState.Error -> {
            item(key = KeyError) {
                ErrorMessage(
                    modifier = Modifier.padding(top = 8.dp),
                    onTryAgainClicked = onTryAgainClicked,
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(TrendingPodcastListParameterProvider::class)
    param: TrendingPodcastListParameter
) {
    PodcastsComposeTheme {
        Surface {
            LazyColumn {
                trendingPodcastList(
                    selectableCategories = param.selectableTrendingCategories,
                    onSelectableCategoryClicked = {},
                    trendingPodcastsState = DiscoverViewModel.TrendingPodcastsState.Success(param.trendingPodcasts),
                    onTrendingPodcastClicked = {},
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
    @PreviewParameter(TrendingPodcastListParameterProvider::class)
    param: TrendingPodcastListParameter
) {
    PodcastsComposeTheme {
        Surface {
            LazyColumn {
                trendingPodcastList(
                    selectableCategories = param.selectableTrendingCategories,
                    onSelectableCategoryClicked = {},
                    trendingPodcastsState = DiscoverViewModel.TrendingPodcastsState.Success(param.trendingPodcasts),
                    onTrendingPodcastClicked = {},
                    onTryAgainClicked = {},
                )
            }
        }
    }
}
