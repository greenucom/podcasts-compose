package com.greencom.android.podcasts2.ui.screen.discover

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.common.component.PodcastItem
import com.greencom.android.podcasts2.ui.screen.discover.component.TrendingCategorySelector
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private const val KeyCategorySelector = "category_selector"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    discoverViewModel: DiscoverViewModel = hiltViewModel(),
) {
    val viewState by discoverViewModel.viewState.collectAsState()

    val trendingPodcasts = viewState.trendingPodcasts
    val trendingPodcastsAlpha by animateFloatAsState(
        if (trendingPodcasts.isNotEmpty()) 1f else 0f
    )

    LazyColumn(modifier = modifier) {

        stickyHeader(key = KeyCategorySelector) {
            Surface {
                TrendingCategorySelector(
                    categories = viewState.trendingCategories,
                    onCategoryClicked = discoverViewModel::onTrendingCategoryClicked,
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                )
            }
        }

        if (trendingPodcasts.isNotEmpty()) {
            itemsIndexed(
                items = trendingPodcasts,
                key = { _, podcast -> podcast.id },
            ) { index, podcast ->
                PodcastItem(
                    modifier = Modifier.alpha(trendingPodcastsAlpha),
                    podcast = podcast,
                    onPodcastClicked = { /* TODO */ },
                )

                if (index != trendingPodcasts.lastIndex) {
                    Divider(
                        modifier = Modifier.alpha(trendingPodcastsAlpha),
                        color = MaterialTheme.colors.onSurfaceUtil,
                    )
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            DiscoverScreen()
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun Dark() {
    PodcastsComposeTheme {
        Surface {
            DiscoverScreen()
        }
    }
}
