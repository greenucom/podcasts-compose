package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.PodcastsIcons
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme

private val SearchBackgroundColorLight = Color(0xFFF0F0F0)
private val SearchBackgroundColorDark = Color(0xFF373737)
private val Colors.searchBackground: Color
    get() = if (isLight) SearchBackgroundColorLight else SearchBackgroundColorDark

private val MinHeight = 40.dp

@Composable
fun SearchPodcastsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.searchBackground,
    )
    val elevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp,
        hoveredElevation = 0.dp,
        focusedElevation = 0.dp,
    )

    Button(
        modifier = modifier.heightIn(min = MinHeight),
        onClick = onClick,
        shape = RoundedCornerShape(percent = 50),
        colors = colors,
        elevation = elevation,
    ) {

        Icon(
            modifier = Modifier.alpha(ContentAlpha.medium),
            imageVector = PodcastsIcons.Search,
            contentDescription = null,
        )

        Text(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
                .alpha(ContentAlpha.high),
            text = stringResource(id = R.string.search_for_podcasts),
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    PodcastsTheme {
        Surface {
            SearchPodcastsButton(
                modifier = Modifier.padding(16.dp),
                onClick = {},
            )
        }
    }
}
