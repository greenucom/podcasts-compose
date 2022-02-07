package com.greencom.android.podcasts2.ui.common.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.OutlinedButtonUtils
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun SubscribeButton(
    modifier: Modifier = Modifier,
    isSubscribed: Boolean,
    onSubscriptionChanged: (isSubscribed: Boolean) -> Unit,
) {
    val buttonColors = if (isSubscribed) {
        ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.primary.copy(
                alpha = OutlinedButtonUtils.CheckedBackgroundColorAlpha
            )
        )
    } else {
        ButtonDefaults.outlinedButtonColors()
    }

    OutlinedButton(
        modifier = modifier,
        onClick = { onSubscriptionChanged(!isSubscribed) },
        shape = RoundedCornerShape(percent = OutlinedButtonUtils.CornerPercent),
        colors = buttonColors,
    ) {
        val icon: ImageVector
        val text: String
        val textColor: Color
        if (isSubscribed) {
            icon = Icons.Outlined.Done
            text = stringResource(R.string.subscribed)
            textColor = MaterialTheme.colors.primary
        } else {
            icon = Icons.Outlined.Add
            text = stringResource(R.string.subscribe)
            textColor = MaterialTheme.colors.onSurface
        }

        Icon(imageVector = icon, contentDescription = null)

        Spacer(Modifier.width(8.dp))

        Text(text = text, color = textColor)
    }
}

@Composable
@Preview(showBackground = true)
private fun LightSubscribe() {
    PodcastsComposeTheme {
        Surface {
            SubscribeButton(
                isSubscribed = false,
                onSubscriptionChanged = {},
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LightSubscribed() {
    PodcastsComposeTheme {
        Surface {
            SubscribeButton(
                isSubscribed = true,
                onSubscriptionChanged = {},
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
private fun DarkSubscribe() {
    PodcastsComposeTheme {
        Surface {
            SubscribeButton(
                isSubscribed = false,
                onSubscriptionChanged = {},
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
private fun DarkSubscribed() {
    PodcastsComposeTheme {
        Surface {
            SubscribeButton(
                isSubscribed = true,
                onSubscriptionChanged = {},
            )
        }
    }
}
