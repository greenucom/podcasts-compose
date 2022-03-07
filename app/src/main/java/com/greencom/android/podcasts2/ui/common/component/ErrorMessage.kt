package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun ErrorMessage(
    onTryAgainClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val imageResId = if (isSystemInDarkTheme()) {
        R.drawable.img_error_dark
    } else {
        R.drawable.img_error_light
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(imageResId),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.something_went_wrong_check_internet_connection),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
        )

        Button(
            modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
            onClick = onTryAgainClicked,
            shape = MaterialTheme.shapes.small,
            elevation = ButtonDefaults.elevation(pressedElevation = 4.dp),
        ) {
            Text(stringResource(R.string.try_again))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            ErrorMessage(
                onTryAgainClicked = {},
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
private fun Dark() {
    PodcastsComposeTheme {
        Surface {
            ErrorMessage(
                onTryAgainClicked = {},
            )
        }
    }
}
