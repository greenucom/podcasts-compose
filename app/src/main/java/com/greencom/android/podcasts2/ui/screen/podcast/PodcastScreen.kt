package com.greencom.android.podcasts2.ui.screen.podcast

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.common.BottomNavBarState
import com.greencom.android.podcasts2.ui.common.component.TopAppBarCustomWithBackButton
import com.greencom.android.podcasts2.ui.common.screenbehavior.ScreenBehavior
import com.greencom.android.podcasts2.ui.common.screenbehavior.SpecificScreenBehavior
import com.greencom.android.podcasts2.ui.screen.podcast.component.PodcastHeader

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PodcastScreen(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PodcastViewModel = hiltViewModel(),
) {

    val screenState = rememberPodcastScreenState()

    SpecificScreenBehavior {
        ScreenBehavior(
            bottomNavBarState = BottomNavBarState.Gone,
        )
    }

    val viewState by viewModel.viewState.collectAsState()

    Crossfade(
        modifier = modifier,
        targetState = viewState,
    ) { state ->

        when (state) {

            PodcastViewModel.ViewState.InitialLoading -> {
                Text(text = "Loading")
            }

            is PodcastViewModel.ViewState.Success -> {
                BackdropScaffold(
                    modifier = modifier,
                    scaffoldState = screenState.backdropScaffoldState,
                    appBar = {
                        TopAppBarCustomWithBackButton(onBackClicked = onBackClicked)
                    },
                    backLayerContent = {
                        PodcastHeader(
                            podcast = state.podcast,
                            onSubscribedChanged = {},
                        )
                    },
                    frontLayerContent = {
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(count = 100, key = { it }) {
                                Text(modifier = Modifier.padding(16.dp), text = "Episode list")
                            }
                        }
                    },
                    backLayerBackgroundColor = MaterialTheme.colors.surface,
                    frontLayerBackgroundColor = MaterialTheme.colors.surface,
                    frontLayerScrimColor = Color.Unspecified,
                    frontLayerElevation = 2.dp,
                )
            }
        }
    }

}
