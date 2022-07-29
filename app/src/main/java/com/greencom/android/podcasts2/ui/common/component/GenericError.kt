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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private val MaxImageSize = 240.dp
private val MinHeightForImage = 420.dp
private const val TextAlpha = 0.87f

@Composable
fun GenericError(
    description: String,
    modifier: Modifier = Modifier,
    image: Painter? = null,
    buttonText: String? = null,
    onButtonClicked: () -> Unit = {},
) {
    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {
        val maxHeight = maxHeight

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (image != null && maxHeight >= MinHeightForImage) {
                Image(
                    modifier = Modifier
                        .widthIn(max = MaxImageSize)
                        .aspectRatio(1f),
                    painter = image,
                    contentDescription = description,
                )
            }

            Text(
                modifier = Modifier.alpha(TextAlpha),
                text = description,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )

            if (buttonText != null) {
                OutlinedButton(
                    modifier = Modifier.padding(top = 12.dp),
                    onClick = onButtonClicked,
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSurfaceUtil),
                ) {
                    Text(text = buttonText)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PodcastsTheme {
        Surface {
            GenericError(
                description = stringResource(id = R.string.something_went_wrong_check_connection),
                buttonText = stringResource(id = R.string.try_again),
                onButtonClicked = {},
                image = painterResource(id = R.drawable.vec_image_connection_error),
            )
        }
    }
}
