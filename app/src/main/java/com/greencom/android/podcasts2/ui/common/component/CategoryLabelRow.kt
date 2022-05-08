package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.common.CategoryHelper
import com.greencom.android.podcasts2.ui.common.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.common.preview.CategoryListPreviewParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private const val MaxCount = 2

@Composable
fun CategoryLabelRow(
    categories: List<CategoryUiModel>,
    modifier: Modifier = Modifier,
    maxCount: Int = MaxCount,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val count = categories.size.coerceAtMost(maxCount)
        for (i in 0 until count) {
            val category = categories[i]
            val name = CategoryHelper.getLocalizedName(category, LocalContext.current)
            if (name != null) {
                CategoryLabel(categoryName = name)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(CategoryListPreviewParameterProvider::class)
    categories: List<CategoryUiModel>,
) {
    PodcastsComposeTheme {
        Surface {
            CategoryLabelRow(
                modifier = Modifier.padding(16.dp),
                categories = categories,
                maxCount = 3,
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
private fun Dark(
    @PreviewParameter(CategoryListPreviewParameterProvider::class)
    categories: List<CategoryUiModel>,
) {
    PodcastsComposeTheme {
        Surface {
            CategoryLabelRow(
                modifier = Modifier.padding(16.dp),
                categories = categories,
                maxCount = 3,
            )
        }
    }
}
