package com.greencom.android.podcasts2.ui.common.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
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
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun SubscribeButton(
    modifier: Modifier = Modifier,
    isSubscribed: Boolean,
    onClick: (isSubscribed: Boolean) -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = { onClick(!isSubscribed) },
        shape = RoundedCornerShape(50),
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
        SubscribeButton(
            isSubscribed = false,
            onClick = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun LightSubscribed() {
    PodcastsComposeTheme {
        SubscribeButton(
            isSubscribed = true,
            onClick = {},
        )
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
        SubscribeButton(
            isSubscribed = false,
            onClick = {},
        )
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
        SubscribeButton(
            isSubscribed = true,
            onClick = {},
        )
    }
}
