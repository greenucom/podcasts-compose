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
fun ConnectionError(
    onTryAgainClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    GenericError(
        modifier = modifier,
        image = painterResource(id = R.drawable.vec_image_connection_error),
        description = stringResource(id = R.string.something_went_wrong_check_connection),
        buttonText = stringResource(id = R.string.try_again),
        onButtonClicked = onTryAgainClicked,
    )
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PodcastsTheme {
        Surface {
            ConnectionError(
                onTryAgainClicked = {},
            )
        }
    }
}
