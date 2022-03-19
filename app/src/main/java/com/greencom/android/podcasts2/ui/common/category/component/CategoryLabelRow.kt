package com.greencom.android.podcasts2.ui.common.category.component

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
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.ui.common.category.CategoryHelper
import com.greencom.android.podcasts2.ui.common.category.previewparameter.CategoriesParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private const val MaxCount = 2

@Composable
fun CategoryLabelRow(
    categories: List<Category>,
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
            val context = LocalContext.current
            val name = CategoryHelper.getLocalizedName(category, context)
            if (name != null) {
                CategoryLabel(name)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(CategoriesParameterProvider::class)
    categories: List<Category>
) {
    PodcastsComposeTheme {
        Surface {
            CategoryLabelRow(
                modifier = Modifier.padding(16.dp),
                categories = categories,
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
    @PreviewParameter(CategoriesParameterProvider::class)
    categories: List<Category>
) {
    PodcastsComposeTheme {
        Surface {
            CategoryLabelRow(
                modifier = Modifier.padding(16.dp),
                categories = categories,
            )
        }
    }
}
