package com.greencom.android.podcasts2.ui.screen.discover

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import com.greencom.android.podcasts2.ui.screen.discover.component.SearchTopBar
import com.greencom.android.podcasts2.ui.screen.discover.component.recommendedPodcastList
import com.greencom.android.podcasts2.ui.screen.discover.component.trendingPodcastList
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoverScreen(
    onPodcastClicked: (podcast: IPodcast) -> Unit,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier,
    discoverViewModel: DiscoverViewModel = hiltViewModel(),
) {
    val discoverScreenState = rememberDiscoverScreenState()

    Scaffold(
        scaffoldState = discoverScreenState.scaffoldState,
        topBar = {
            SearchTopBar(onSearchClicked = onSearchClicked)
        },
    ) { paddingValues ->
        val viewState by discoverViewModel.viewState.collectAsState()

        val trendingPodcastsAlpha by animateFloatAsState(
            if (viewState.trendingPodcasts.isNotEmpty()) 1f else 0f
        )

        LazyColumn(
            modifier = modifier,
            state = discoverScreenState.screenLazyColumnState,
            contentPadding = paddingValues,
        ) {

            recommendedPodcastList(
                state = discoverScreenState.recommendedPodcastsLazyRowState,
                recommendedPodcasts = viewState.trendingPodcasts,
                onRecommendedPodcastClicked = onPodcastClicked,
                contentPadding = PaddingValues(top = 8.dp),
            )

            trendingPodcastList(
                selectableCategories = viewState.trendingCategories,
                onSelectableCategoryClicked = discoverViewModel::onSelectableTrendingCategoryClicked,
                trendingPodcasts = viewState.trendingPodcasts,
                onTrendingPodcastClicked = onPodcastClicked,
                contentAlpha = trendingPodcastsAlpha,
                paddingTop = 16.dp,
            )

        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            DiscoverScreen(
                onPodcastClicked = {},
                onSearchClicked = {},
            )
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
            DiscoverScreen(
                onPodcastClicked = {},
                onSearchClicked = {},
            )
        }
    }
}
