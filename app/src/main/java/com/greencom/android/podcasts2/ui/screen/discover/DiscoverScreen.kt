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
    navigateToPodcastScreen: (Podcast) -> Unit,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DiscoverViewModel = hiltViewModel(),
) {

    val screenState = rememberDiscoverScreenState(
        onSearchClicked = onSearchClicked,
    )

    SpecificScreenBehavior {
        ScreenBehavior(
            onBottomNavBarItemReselected = { screenState.onBottomNavBarItemReselected() },
        )
    }

    val state by viewModel.state.collectAsState()

    state.showPodcastScreen?.let { podcast ->
        navigateToPodcastScreen(podcast)
        val event = DiscoverViewEvent.PodcastScreenShown
        viewModel.dispatchEvent(event)
    }

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
                dispatchEvent = viewModel::dispatchEvent,
                innerLazyListState = screenState.recommendedPodcastsListState,
                recommendedPodcastsState = state.recommendedPodcastsState,
            )

            trendingPodcastsSection(
                dispatchEvent = viewModel::dispatchEvent,
                selectableCategories = state.selectableCategories,
                trendingPodcastsState = state.trendingPodcastsState,
            )
        }
    }
}
