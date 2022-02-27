package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.size.Scale
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import com.greencom.android.podcasts2.ui.common.rememberImagePainterCustom
import com.greencom.android.podcasts2.ui.screen.discover.previewparameter.TrendingPodcastParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PodcastItem(
    podcast: IPodcast,
    onPodcastClicked: (podcast: IPodcast) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        onClick = { onPodcastClicked(podcast) },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Image(
                    modifier = Modifier
                        .size(PodcastItemUtils.ImageSizeDp)
                        .clip(MaterialTheme.shapes.medium),
                    painter = rememberImagePainterCustom(podcast.image) {
                        scale(Scale.FILL)
                    },
                    contentDescription = stringResource(R.string.podcast_cover),
                )

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = podcast.title,
                        style = PodcastItemUtils.TitleTextStyle,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )

                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            modifier = Modifier.padding(top = 4.dp),
                            text = podcast.author,
                            style = MaterialTheme.typography.caption,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = podcast.description,
                style = MaterialTheme.typography.body2,
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
        PodcastItem(
            podcast = podcast,
            onPodcastClicked = {},
        )
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
        PodcastItem(
            podcast = podcast,
            onPodcastClicked = {},
        )
    }
}
