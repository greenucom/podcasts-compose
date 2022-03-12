package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.ui.common.previewparameter.CategoriesParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private const val CategoriesMaxCount = 2

@Composable
fun CategoryLabelRow(
    categories: List<Category>,
    modifier: Modifier = Modifier,
) {
    val count = categories.size.coerceAtMost(CategoriesMaxCount)
    if (count > 0) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            for (i in 0 until count) {
                CategoryLabel(categories[i].displayName)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(CategoriesParameterProvider::class)
    categories: List<Category>,
) {
    PodcastsComposeTheme {
        Surface {
            CategoryLabelRow(categories)
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
    categories: List<Category>,
) {
    PodcastsComposeTheme {
        Surface {
            CategoryLabelRow(categories)
        }
    }
}
