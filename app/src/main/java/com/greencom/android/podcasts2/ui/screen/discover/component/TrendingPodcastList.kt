package com.greencom.android.podcasts2.ui.screen.discover.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import com.greencom.android.podcasts2.ui.common.component.PodcastItem
import com.greencom.android.podcasts2.ui.screen.discover.model.SelectableTrendingCategory
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
) {
    item(key = KeyHeader) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            text = stringResource(R.string.trending),
            style = MaterialTheme.typography.h4,
        )
    }

    stickyHeader(key = KeyCategorySelector) {
        TrendingCategorySelector(
            categories = selectableTrendingCategories,
            onCategoryClicked = onSelectableTrendingCategoryClicked,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
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
