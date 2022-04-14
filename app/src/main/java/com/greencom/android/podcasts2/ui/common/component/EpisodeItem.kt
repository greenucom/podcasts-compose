package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.domain.episode.Episode
import com.greencom.android.podcasts2.ui.common.EpisodeHelper
import com.greencom.android.podcasts2.ui.common.Symbol
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private const val FormatSpaceAround = " %1\$s "

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EpisodeItem(
    episode: Episode,
    onEpisodeClicked: (Episode) -> Unit,
    modifier: Modifier = Modifier,
) {

    Surface(
        modifier = modifier.fillMaxWidth(),
        onClick = { onEpisodeClicked(episode) },
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 12.dp),
        ) {

            Row {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {

                    Text(
                        text = EpisodeHelper.getFormattedNumber(episode, LocalContext.current),
                        style = MaterialTheme.typography.caption,
                    )

                    Text(
                        text = FormatSpaceAround.format(Symbol.Dot),
                        style = MaterialTheme.typography.caption,
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {

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

        }
    }
}
