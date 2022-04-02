package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SubscribeButton(
    isSubscribed: Boolean,
    onSubscribedChanged: (isSubscribed: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {

    OutlinedButtonCustom(
        modifier = modifier,
        isChecked = isSubscribed,
        onClick = { onSubscribedChanged(!isSubscribed) },
    ) {

        AnimatedContent(
            modifier = Modifier.padding(end = 4.dp),
            targetState = isSubscribed,
            transitionSpec = { scaleIn() + fadeIn() with scaleOut() + fadeOut() }
        ) { isSubscribed ->
            val icon = if (isSubscribed) Icons.Outlined.Done else Icons.Outlined.Add
            Icon(imageVector = icon, contentDescription = null)
        }

        AnimatedContent(
            targetState = isSubscribed,
            transitionSpec = {
                val slideInSign = if (isSubscribed) -1 else 1
                val slideOutSign = if (isSubscribed) 1 else -1
                (slideInVertically { it * slideInSign } + fadeIn() with
                        slideOutVertically { it * slideOutSign } + fadeOut())
                    .using(SizeTransform(clip = false))
            },
        ) { isSubscribed ->
            val textResId = if (isSubscribed) R.string.subscribed else R.string.subscribe
            val textColorTargetValue = if (isSubscribed) {
                MaterialTheme.colors.primary
            } else {
                MaterialTheme.colors.onSurface
            }
            val textColor by animateColorAsState(
                targetValue = textColorTargetValue,
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            )

            Text(
                text = stringResource(textResId),
                style = MaterialTheme.typography.body2,
                color = textColor,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SubscribeLight() {
    PodcastsComposeTheme {
        var isSubscribed by remember { mutableStateOf(false) }
        Surface {
            SubscribeButton(
                modifier = Modifier.padding(16.dp),
                isSubscribed = isSubscribed,
                onSubscribedChanged = { isSubscribed = it },
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SubscribedLight() {
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

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun SubscribeDark() {
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
private fun SubscribedDark() {
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
