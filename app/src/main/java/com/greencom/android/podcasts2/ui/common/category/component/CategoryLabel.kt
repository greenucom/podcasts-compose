package com.greencom.android.podcasts2.ui.common.category.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private const val TextColorAlpha = 0.74f

private val CategoryLabelBackgroundLight = Color(0xFFEFEFEF)
private val CategoryLabelBackgroundDark = Color(0xFF323232)
private val Colors.categoryLabelBackground: Color
    get() = if (isLight) CategoryLabelBackgroundLight else CategoryLabelBackgroundDark

@Composable
fun CategoryLabel(
    name: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.categoryLabelBackground,
        shape = RoundedCornerShape(4.dp),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
            text = name,
            style = MaterialTheme.typography.overline,
            color = MaterialTheme.colors.onSurface.copy(alpha = TextColorAlpha),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            CategoryLabel(
                modifier = Modifier.padding(16.dp),
                name = "News"
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
            CategoryLabel(
                modifier = Modifier.padding(16.dp),
                name = "Новости"
            )
        }
    }
}
