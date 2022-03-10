package com.greencom.android.podcasts2.ui.screen.discover

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import com.greencom.android.podcasts2.ui.common.plus
import com.greencom.android.podcasts2.ui.common.rememberTotalBottomBarsHeight
import com.greencom.android.podcasts2.ui.navigation.BottomNavBarItem
import com.greencom.android.podcasts2.ui.screen.app.AppViewModel
import com.greencom.android.podcasts2.ui.screen.discover.component.DiscoverSearchTopBar
import com.greencom.android.podcasts2.ui.screen.discover.component.recommendedPodcastList
import com.greencom.android.podcasts2.ui.screen.discover.component.trendingPodcastList
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoverScreen(
    onPodcastClicked: (podcast: IPodcast) -> Unit,
    onSearchClicked: () -> Unit,
    appViewModel: AppViewModel,
    modifier: Modifier = Modifier,
    discoverViewModel: DiscoverViewModel = hiltViewModel(),
) {
    val screenState = rememberDiscoverScreenState()

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        topBar = { DiscoverSearchTopBar(onSearchClicked = onSearchClicked) },
    ) { paddingValues ->
        val recommendedPodcastsState by discoverViewModel.recommendedPodcastsState.collectAsState()
        val trendingCategories by discoverViewModel.trendingCategories.collectAsState()
        val trendingPodcastsState by discoverViewModel.trendingPodcastsState.collectAsState()

        val appState by appViewModel.appState.collectAsState()

        LaunchedEffect(appState.reselectedBottomNavBarItem) {
            if (appState.reselectedBottomNavBarItem == BottomNavBarItem.Discover) {
                if (
                    screenState.screenLazyColumnState.firstVisibleItemIndex == 0 &&
                    screenState.screenLazyColumnState.firstVisibleItemScrollOffset == 0
                ) {
                    onSearchClicked()
                } else {
                    screenState.screenLazyColumnState.animateScrollToItem(0)
                }
                appViewModel.onReselectedBottomNavBarItemHandled()
            }
        }

        val totalBottomBarsHeight = rememberTotalBottomBarsHeight()
        val layoutDirection = LocalLayoutDirection.current
        val paddingValues = remember(paddingValues, totalBottomBarsHeight) {
            paddingValues.plus(
                bottom = totalBottomBarsHeight,
                layoutDirection = layoutDirection,
            )
        }

        LazyColumn(
            state = screenState.screenLazyColumnState,
            contentPadding = paddingValues,
        ) {

            recommendedPodcastList(
                modifier = Modifier.padding(vertical = 8.dp),
                lazyRowState = screenState.recommendedPodcastsLazyRowState,
                recommendedPodcastsState = recommendedPodcastsState,
                onRecommendedPodcastClicked = onPodcastClicked,
            )

            trendingPodcastList(
                selectableCategories = trendingCategories,
                onSelectableCategoryClicked = discoverViewModel::onSelectableTrendingCategoryClicked,
                trendingPodcastsState = trendingPodcastsState,
                onTrendingPodcastClicked = onPodcastClicked,
                onTryAgainClicked = discoverViewModel::onTryAgainClicked,
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
                appViewModel = hiltViewModel(),
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
                appViewModel = hiltViewModel(),
            )
        }
    }
}
