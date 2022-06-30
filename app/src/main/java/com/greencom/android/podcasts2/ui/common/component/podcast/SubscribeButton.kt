package com.greencom.android.podcasts2.ui.common.component.podcast

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.PodcastsIcons
import com.greencom.android.podcasts2.ui.common.component.PodcastsOutlinedButton
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SubscribeButton(
    isUserSubscribed: Boolean,
    onIsUserSubscribedChanged: (isSubscribed: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {

    PodcastsOutlinedButton(
        modifier = modifier,
        isChecked = isUserSubscribed,
        onClick = { onIsUserSubscribedChanged(!isUserSubscribed) },
        contentPadding = PaddingValues(start = 8.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
    ) {

        AnimatedContent(
            modifier = Modifier.padding(end = 4.dp),
            targetState = isUserSubscribed,
            transitionSpec = { scaleIn() + fadeIn() with scaleOut() + fadeOut() }
        ) { isUserSubscribed ->
            val icon = if (isUserSubscribed) PodcastsIcons.Done else PodcastsIcons.Add
            Icon(imageVector = icon, contentDescription = null)
        }

        AnimatedContent(
            targetState = isUserSubscribed,
            transitionSpec = {
                val slideInSign = if (isUserSubscribed) -1 else 1
                val slideOutSign = if (isUserSubscribed) 1 else -1
                (slideInVertically { it * slideInSign } + fadeIn() with
                        slideOutVertically { it * slideOutSign } + fadeOut())
                    .using(SizeTransform(clip = false))
            },
        ) { isUserSubscribed ->
            val textResId = if (isUserSubscribed) R.string.subscribed else R.string.subscribe
            val textColorTargetValue = if (isUserSubscribed) {
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

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PodcastsTheme {
        Surface {
            var isUserSubscribed by remember { mutableStateOf(false) }
            SubscribeButton(
                isUserSubscribed = isUserSubscribed,
                onIsUserSubscribedChanged = {
                    isUserSubscribed = !isUserSubscribed
                },
            )
        }
    }
}
