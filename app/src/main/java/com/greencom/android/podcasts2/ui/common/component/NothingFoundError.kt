package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme

@Composable
fun NothingFoundError(modifier: Modifier = Modifier) {
    GenericError(
        modifier = modifier,
        image = painterResource(id = R.drawable.vec_image_nothing_found_error),
        description = stringResource(id = R.string.nothing_found),
    )
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PodcastsTheme {
        Surface {
            NothingFoundError()
        }
    }
}
