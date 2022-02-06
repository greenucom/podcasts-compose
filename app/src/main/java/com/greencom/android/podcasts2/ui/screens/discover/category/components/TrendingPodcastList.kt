package com.greencom.android.podcasts2.ui.screens.discover.category.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.greencom.android.podcasts2.domain.podcasts.IPodcast
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast

@Composable
fun TrendingPodcastList(
    modifier: Modifier = Modifier,
    podcasts: List<TrendingPodcast>,
    onPodcastClicked: (podcast: IPodcast) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(items = podcasts, key = TrendingPodcast::id) { podcast ->
            TrendingPodcastItem(
                podcast = podcast,
                onClick = onPodcastClicked,
            )

            if (podcast != podcasts.lastOrNull()) {
                Divider()
            }
        }
    }
}
