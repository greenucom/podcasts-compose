package com.greencom.android.podcasts2.ui.screen.discover

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.screen.discover.component.recommendedPodcastList
import com.greencom.android.podcasts2.ui.screen.discover.component.trendingPodcastList
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private const val KeyRecommendedPodcastListSpacer = "recommended_podcast_list_spacer"
private const val KeyTrendingPodcastListSpacer = "trending_podcast_list_spacer"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    discoverViewModel: DiscoverViewModel = hiltViewModel(),
) {
    val viewState by discoverViewModel.viewState.collectAsState()

    val trendingPodcastsAlpha by animateFloatAsState(
        if (viewState.trendingPodcasts.isNotEmpty()) 1f else 0f
    )

    LazyColumn(modifier = modifier) {

        item(key = KeyRecommendedPodcastListSpacer) {
            Spacer(Modifier.height(8.dp))
        }
        recommendedPodcastList(
            recommendedPodcasts = viewState.trendingPodcasts,
            onRecommendedPodcastClicked = { /* TODO */ },
        )

        item(key = KeyTrendingPodcastListSpacer) {
            Spacer(Modifier.height(16.dp))
        }
        trendingPodcastList(
            selectableTrendingCategories = viewState.trendingCategories,
            onSelectableTrendingCategoryClicked = discoverViewModel::onTrendingCategoryClicked,
            trendingPodcasts = viewState.trendingPodcasts,
            onTrendingPodcastClicked = { /* TODO */ },
            contentAlpha = trendingPodcastsAlpha,
        )

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
