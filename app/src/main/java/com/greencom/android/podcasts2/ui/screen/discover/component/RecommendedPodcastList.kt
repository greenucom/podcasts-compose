package com.greencom.android.podcasts2.ui.screen.discover.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcast.IPodcast

private const val KeyRecommendedPodcastList = "recommended_podcast_list"

@OptIn(ExperimentalAnimationApi::class)
fun LazyListScope.recommendedPodcastList(
    recommendedPodcasts: List<IPodcast>,
    onRecommendedPodcastClicked: (podcast: IPodcast) -> Unit,
) {
    item(key = KeyRecommendedPodcastList) {
        Column {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                text = stringResource(R.string.recommended),
                style = MaterialTheme.typography.h4,
            )

            Spacer(Modifier.height(8.dp))

            AnimatedContent(recommendedPodcasts.isNotEmpty()) { isNotEmpty ->
                if (isNotEmpty) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(
                            items = recommendedPodcasts,
                            key = { it.id },
                        ) { podcast ->
                            PodcastCard(
                                podcast = podcast,
                                onPodcastClicked = onRecommendedPodcastClicked,
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//private fun Light(
//    @PreviewParameter(TrendingPodcastsParameterProvider::class) podcasts: List<TrendingPodcast>,
//) {
//    PodcastsComposeTheme {
//        Surface {
//            RecommendedPodcastList(
//                podcasts = podcasts,
//                onPodcastClicked = {},
//            )
//        }
//    }
//}
//
//@Composable
//@Preview(
//    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    locale = "ru",
//)
//private fun Dark(
//    @PreviewParameter(TrendingPodcastsParameterProvider::class) podcasts: List<TrendingPodcast>,
//) {
//    PodcastsComposeTheme {
//        Surface {
//            RecommendedPodcastList(
//                podcasts = podcasts,
//                onPodcastClicked = {},
//            )
//        }
//    }
//}
