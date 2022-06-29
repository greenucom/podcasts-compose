package com.greencom.android.podcasts2.ui.common.component.category

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
import com.greencom.android.podcasts2.ui.model.category.CategoryDisplayNameResolver
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.previewparameter.category.CategoryUiModelListPreviewParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme

private const val MaxCountDefault = 2

@Composable
fun CategoryLabelRow(
    categories: List<CategoryUiModel>,
    modifier: Modifier = Modifier,
    maxCount: Int = MaxCountDefault,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val count = categories.size.coerceAtMost(maxCount)
        for (i in 0 until count) {
            val category = categories[i]
            val name = CategoryDisplayNameResolver.getCategoryDisplayName(category, LocalContext.current)
            if (name != null) {
                CategoryLabel(categoryName = name)
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview(
    @PreviewParameter(CategoryUiModelListPreviewParameterProvider::class)
    categories: List<CategoryUiModel>,
) {
    PodcastsTheme {
        Surface {
            CategoryLabelRow(
                modifier = Modifier.padding(16.dp),
                categories = categories,
                maxCount = 3,
            )
        }
    }
}
