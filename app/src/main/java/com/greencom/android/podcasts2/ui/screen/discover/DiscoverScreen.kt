package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.ScreenBehavior
import com.greencom.android.podcasts2.ui.screen.discover.component.DiscoverTopBar
import com.greencom.android.podcasts2.ui.screen.discover.component.recommendedPodcastsSection
import com.greencom.android.podcasts2.ui.screen.discover.component.trendingPodcastsSection

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoverScreen(
    onPodcastClicked: (Podcast) -> Unit,
    onSearchClicked: () -> Unit,
    onScreenBehaviorChanged: (ScreenBehavior) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DiscoverViewModel = hiltViewModel(),
) {

    val screenState = rememberDiscoverScreenState(
        onSearchClicked = onSearchClicked,
    )

    LaunchedEffect(Unit) {
        val behavior = ScreenBehavior(
            onBottomNavBarItemReselected = {
                screenState.onBottomNavBarItemReselected()
            },
        )
        onScreenBehaviorChanged(behavior)
    }

    val recommendedPodcastsState by viewModel.recommendedPodcastsState.collectAsState()
    val selectableCategories by viewModel.selectableCategories.collectAsState()
    val trendingPodcastsState by viewModel.trendingPodcastsState.collectAsState()

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
                innerLazyListState = screenState.recommendedPodcastsListState,
                recommendedPodcastsState = recommendedPodcastsState,
                onPodcastClicked = onPodcastClicked,
            )

            trendingPodcastsSection(
                selectableCategories = selectableCategories,
                onCategoryClicked = viewModel::onSelectableCategoryClicked,
                trendingPodcastsState = trendingPodcastsState,
                onPodcastClicked = onPodcastClicked,
                onSubscribedChanged = viewModel::onSubscribedChanged,
                onTryAgainClicked = viewModel::onTryAgainClicked,
            )
        }
    }
}
