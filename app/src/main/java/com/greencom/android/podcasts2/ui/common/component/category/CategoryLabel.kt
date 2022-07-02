package com.greencom.android.podcasts2.ui.common.component.category

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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.previewparameter.category.CategoryUiModelPreviewParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme

private val BackgroundLight = Color(0xFFEFEFEF)
private val BackgroundDark = Color(0xFF323232)
private val Colors.categoryLabelBackground: Color
    get() = if (isLight) BackgroundLight else BackgroundDark

private const val TextAlpha = 0.74f

@Composable
fun CategoryLabel(
    categoryName: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.categoryLabelBackground,
        shape = RoundedCornerShape(4.dp),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
            text = categoryName,
            style = MaterialTheme.typography.overline,
            color = MaterialTheme.colors.onSurface.copy(alpha = TextAlpha),
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview(
    @PreviewParameter(CategoryUiModelPreviewParameterProvider::class)
    category: CategoryUiModel,
) {
    PodcastsTheme {
        Surface {
            CategoryLabel(
                modifier = Modifier.padding(16.dp),
                categoryName = category.name,
            )
        }
    }
}
