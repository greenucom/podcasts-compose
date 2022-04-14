package com.greencom.android.podcasts2.ui.screen.mypodcasts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.greencom.android.podcasts2.ui.screen.app.ScreenBehaviorTag
import timber.log.Timber

@Composable
fun MyPodcastsScreen(
    modifier: Modifier = Modifier,
) {
    Timber.tag(ScreenBehaviorTag).d("MyPodcastsScreen")
}
