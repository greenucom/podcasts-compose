package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.screenbehavior.ScreenBehavior
import com.greencom.android.podcasts2.ui.common.screenbehavior.SpecificScreenBehavior
import com.greencom.android.podcasts2.ui.screen.discover.component.DiscoverTopBar
import com.greencom.android.podcasts2.ui.screen.discover.component.recommendedPodcastsSection
import com.greencom.android.podcasts2.ui.screen.discover.component.trendingPodcastsSection

@Composable
fun DiscoverScreen(
    onPodcastClicked: (Podcast) -> Unit,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DiscoverViewModel = hiltViewModel(),
) {

    val screenState = rememberDiscoverScreenState(
        onPodcastClicked = onPodcastClicked,
        onSearchClicked = onSearchClicked,
    )

    SpecificScreenBehavior {
        ScreenBehavior(
            onBottomNavBarItemReselected = { screenState.onBottomNavBarItemReselected() },
        )
    }

    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        topBar = { DiscoverTopBar(onSearchClicked = onSearchClicked) },
    ) { paddingValues ->

        LazyColumn(
            state = screenState.screenListState,
            contentPadding = paddingValues,
        ) {

            recommendedPodcastsSection(
                modifier = Modifier.padding(vertical = 8.dp),
                dispatchIntent = viewModel::dispatchIntent,
                innerLazyListState = screenState.recommendedPodcastsListState,
                recommendedPodcastsState = state.recommendedPodcastsState,
            )

            trendingPodcastsSection(
                dispatchIntent = viewModel::dispatchIntent,
                selectableCategories = state.selectableCategories,
                trendingPodcastsState = state.trendingPodcastsState,
            )
        }
    }
}
