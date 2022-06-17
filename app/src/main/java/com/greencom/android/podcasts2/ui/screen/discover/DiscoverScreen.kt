package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.screen.discover.component.SearchPodcastsButton

@Composable
fun DiscoverScreen(
    onSearchPodcastsClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DiscoverViewModel = hiltViewModel(),
) {
    val screenState = rememberDiscoverScreenState()

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        topBar = { DiscoverTopBar(onSearchPodcastsClicked = onSearchPodcastsClicked) },
    ) { paddingValues ->


    }
}

@Composable
private fun DiscoverTopBar(
    onSearchPodcastsClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        val surfaceColor = MaterialTheme.colors.surface
        Spacer(
            modifier = Modifier
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .fillMaxWidth()
                .drawBehind { drawRect(color = surfaceColor) }
        )

        TopAppBar(
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 0.dp,
            contentPadding = PaddingValues(0.dp),
        ) {
            SearchPodcastsButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = onSearchPodcastsClicked,
            )
        }
    }
}
