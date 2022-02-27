package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import com.greencom.android.podcasts2.ui.common.component.PodcastItem
import com.greencom.android.podcasts2.ui.screen.discover.model.SelectableTrendingCategory
import com.greencom.android.podcasts2.ui.screen.discover.previewparameter.TrendingPodcastListParameter
import com.greencom.android.podcasts2.ui.screen.discover.previewparameter.TrendingPodcastListParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private const val KeyHeader = "trending_podcast_list_header"
private const val KeyCategorySelector = "trending_podcast_list_category_selector"

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.trendingPodcastList(
    selectableTrendingCategories: List<SelectableTrendingCategory>,
    onSelectableTrendingCategoryClicked: (category: SelectableTrendingCategory) -> Unit,
    trendingPodcasts: List<TrendingPodcast>,
    onTrendingPodcastClicked: (podcast: IPodcast) -> Unit,
    contentAlpha: Float,
    paddingTop: Dp = 0.dp,
) {
    item(key = KeyHeader) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = paddingTop, bottom = 4.dp),
            text = stringResource(R.string.trending),
            style = MaterialTheme.typography.h4,
        )
    }

    stickyHeader(key = KeyCategorySelector) {
        TrendingCategorySelector(
            categories = selectableTrendingCategories,
            onCategoryClicked = onSelectableTrendingCategoryClicked,
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 4.dp)
        )
    }

    if (trendingPodcasts.isNotEmpty()) {
        itemsIndexed(
            items = trendingPodcasts,
            key = { _, podcast -> podcast.id },
        ) { index, podcast ->
            PodcastItem(
                modifier = Modifier.alpha(contentAlpha),
                podcast = podcast,
                onPodcastClicked = onTrendingPodcastClicked,
            )

            if (index != trendingPodcasts.lastIndex) {
                Divider(
                    modifier = Modifier.alpha(contentAlpha),
                    color = MaterialTheme.colors.onSurfaceUtil,
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
                    selectableTrendingCategories = param.selectableTrendingCategories,
                    onSelectableTrendingCategoryClicked = {},
                    trendingPodcasts = param.trendingPodcasts,
                    onTrendingPodcastClicked = {},
                    contentAlpha = 1f,
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
                    selectableTrendingCategories = param.selectableTrendingCategories,
                    onSelectableTrendingCategoryClicked = {},
                    trendingPodcasts = param.trendingPodcasts,
                    onTrendingPodcastClicked = {},
                    contentAlpha = 1f,
                )
            }
        }
    }
}
