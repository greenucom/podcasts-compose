package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.size.Scale
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import com.greencom.android.podcasts2.ui.common.component.CategoryLabelRow
import com.greencom.android.podcasts2.ui.common.rememberImagePainterCustom
import com.greencom.android.podcasts2.ui.screen.discover.previewparameter.TrendingPodcastParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PodcastCard(
    podcast: IPodcast,
    onPodcastClicked: (podcast: IPodcast) -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val cardWidthDp = minOf(
        PodcastCardUtils.MaxWidthDp,
        screenWidthDp * PodcastCardUtils.MaxWidthPercentOfScreen,
    )

    Card(
        modifier = modifier.width(cardWidthDp),
        onClick = { onPodcastClicked(podcast) },
        shape = MaterialTheme.shapes.large,
        elevation = 6.dp,
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberImagePainterCustom(podcast.image) {
                        scale(Scale.FILL)
                    },
                    contentDescription = stringResource(R.string.podcast_cover),
                )

                CategoryLabelRow(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    categories = podcast.categories,
                )
            }

            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 16.dp),
                text = PodcastCardUtils.handlePodcastTitle(podcast.title),
                style = MaterialTheme.typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(TrendingPodcastParameterProvider::class)
    podcast: TrendingPodcast
) {
    PodcastsComposeTheme {
        Surface {
            PodcastCard(
                modifier = Modifier.padding(16.dp),
                podcast = podcast,
                onPodcastClicked = {},
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
private fun Dark(
    @PreviewParameter(TrendingPodcastParameterProvider::class)
    podcast: TrendingPodcast
) {
    PodcastsComposeTheme {
        Surface {
            PodcastCard(
                modifier = Modifier.padding(16.dp),
                podcast = podcast,
                onPodcastClicked = {},
            )
        }
    }
}
