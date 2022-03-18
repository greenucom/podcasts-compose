package com.greencom.android.podcasts2.ui.common.podcast.component

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.component.OutlinedButtonUtils
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SubscribeButton(
    isSubscribed: Boolean,
    onSubscribedChanged: (isSubscribed: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = OutlinedButtonUtils.backgroundColor(isSubscribed)
    val buttonColors = ButtonDefaults.outlinedButtonColors(
        backgroundColor = backgroundColor,
    )

    val borderColor by animateColorAsState(OutlinedButtonUtils.borderColor(isSubscribed))

    OutlinedButton(
        modifier = modifier,
        onClick = { onSubscribedChanged(!isSubscribed) },
        shape = MaterialTheme.shapes.small,
        colors = buttonColors,
        border = BorderStroke(width = 1.dp, color = borderColor),
        contentPadding = OutlinedButtonUtils.contentPadding,
    ) {

        AnimatedContent(
            modifier = Modifier.align(Alignment.CenterVertically),
            targetState = isSubscribed,
            transitionSpec = { scaleIn() + fadeIn() with scaleOut() + fadeOut() },
        ) { isSubscribed ->
            val icon = if (isSubscribed) Icons.Outlined.Done else Icons.Outlined.Add
            Icon(imageVector = icon, contentDescription = null)
        }

        AnimatedContent(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 6.dp),
            targetState = isSubscribed,
            transitionSpec = {
                if (isSubscribed) {
                    slideInVertically { -it } + fadeIn() with slideOutVertically { it } + fadeOut()
                } else {
                    slideInVertically { it } + fadeIn() with slideOutVertically { -it } + fadeOut()
                }.using(SizeTransform(clip = false))
            },
        ) { isSubscribed ->
            val resId = if (isSubscribed) R.string.subscribed else R.string.subscribe
            val color by animateColorAsState(OutlinedButtonUtils.contentColor(isSubscribed))
            Text(
                text = stringResource(resId),
                style = MaterialTheme.typography.body2,
                color = color,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            SubscribeButton(
                modifier = Modifier.padding(16.dp),
                isSubscribed = false,
                onSubscribedChanged = {},
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
            SubscribeButton(
                modifier = Modifier.padding(16.dp),
                isSubscribed = true,
                onSubscribedChanged = {},
            )
        }
    }
}
