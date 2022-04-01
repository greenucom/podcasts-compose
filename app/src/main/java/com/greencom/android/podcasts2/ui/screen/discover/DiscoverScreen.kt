package com.greencom.android.podcasts2.ui.screen.discover

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.LocalContentBottomPadding
import com.greencom.android.podcasts2.ui.common.copy
import com.greencom.android.podcasts2.ui.screen.app.AppViewModel
import com.greencom.android.podcasts2.ui.screen.discover.component.DiscoverSearchTopBar
import com.greencom.android.podcasts2.ui.screen.discover.component.recommendedPodcastList
import com.greencom.android.podcasts2.ui.screen.discover.component.trendingPodcastList
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoverScreen(
    onPodcastClicked: (podcast: Podcast) -> Unit,
    onSearchClicked: () -> Unit,
    appViewModel: AppViewModel,
    modifier: Modifier = Modifier,
    discoverViewModel: DiscoverViewModel = hiltViewModel(),
) {
    val screenState = rememberDiscoverScreenState(
        onSearchClicked = onSearchClicked,
    )

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        topBar = { DiscoverSearchTopBar(onSearchClicked = onSearchClicked) },
    ) { paddingValues ->
        val recommendedPodcastsState by discoverViewModel.recommendedPodcastsState.collectAsState()
        val trendingCategories by discoverViewModel.trendingCategories.collectAsState()
        val trendingPodcastsState by discoverViewModel.trendingPodcastsState.collectAsState()

        LaunchedEffect(Unit) {
            appViewModel.viewEvents.collect { event ->
                screenState.handleAppEvent(event)
            }
        }

        val listContentPadding = paddingValues.copy(
            bottom = LocalContentBottomPadding.current.bottomPadding,
        )

        LazyColumn(
            state = screenState.lazyColumnState,
            contentPadding = listContentPadding,
        ) {

            recommendedPodcastList(
                modifier = Modifier.padding(vertical = 8.dp),
                lazyRowState = screenState.recommendedPodcastsLazyRowState,
                recommendedPodcastsState = recommendedPodcastsState,
                onPodcastClicked = onPodcastClicked,
            )

            trendingPodcastList(
                selectableCategories = trendingCategories,
                onSelectableCategoryClicked = discoverViewModel::onSelectableTrendingCategoryClicked,
                trendingPodcastsState = trendingPodcastsState,
                onPodcastClicked = onPodcastClicked,
                onSubscribedChanged = discoverViewModel::onSubscribedChanged,
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
