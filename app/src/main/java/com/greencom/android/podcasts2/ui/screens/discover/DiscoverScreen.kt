package com.greencom.android.podcasts2.ui.screens.discover

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcasts.IPodcast
import com.greencom.android.podcasts2.ui.common.CrossfadeAnimatedVisibility
import com.greencom.android.podcasts2.ui.common.components.PodcastItem
import com.greencom.android.podcasts2.ui.common.screen.LoadingScreen
import com.greencom.android.podcasts2.ui.screens.discover.DiscoverViewModel.ViewState
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    discoverViewModel: DiscoverViewModel = hiltViewModel(),
) {
    Surface(modifier = modifier) {
        val viewState by discoverViewModel.viewState.collectAsState()

        CrossfadeAnimatedVisibility(viewState is ViewState.InitialLoading) {
            LoadingScreen(text = stringResource(R.string.loading_podcasts))
        }

        CrossfadeAnimatedVisibility(viewState is ViewState.PodcastList) {
            val state = viewState as ViewState.PodcastList
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(items = state.podcasts, key = IPodcast::id) { podcast ->
                    PodcastItem(
                        podcast = podcast,
                        onPodcastClicked = discoverViewModel::onPodcastClicked,
                    )
                }
            }
        }

        CrossfadeAnimatedVisibility(viewState is ViewState.Error) {

        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        DiscoverScreen()
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
        DiscoverScreen()
    }
}
