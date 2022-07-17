package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private val MaxImageSize = 240.dp
private const val TextAlpha = 0.87f

@Composable
fun NothingFoundError(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .widthIn(max = MaxImageSize)
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.vec_nothing_found_error),
            contentDescription = stringResource(id = R.string.nothing_found),
        )

        Text(
            modifier = Modifier.alpha(TextAlpha),
            text = stringResource(id = R.string.nothing_found),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PodcastsTheme {
        Surface {
            NothingFoundError()
        }
    }
}
