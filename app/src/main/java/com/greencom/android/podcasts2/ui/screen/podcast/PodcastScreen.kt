package com.greencom.android.podcasts2.ui.screen.podcast

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.common.BottomNavBarState
import com.greencom.android.podcasts2.ui.common.component.TopAppBarCustomWithBackButton
import com.greencom.android.podcasts2.ui.common.screenbehavior.ScreenBehavior
import com.greencom.android.podcasts2.ui.common.screenbehavior.SpecificScreenBehavior

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

    BackdropScaffold(
        modifier = modifier,
        scaffoldState = screenState.backdropScaffoldState,
        appBar = {
            TopAppBarCustomWithBackButton(onBackClicked = onBackClicked)
        },
        backLayerContent = {
            Text(modifier = Modifier.padding(16.dp), text = "Podcast header")
        },
        frontLayerContent = {
            Text(modifier = Modifier.padding(16.dp), text = "Episode list")
        },
    )

}
