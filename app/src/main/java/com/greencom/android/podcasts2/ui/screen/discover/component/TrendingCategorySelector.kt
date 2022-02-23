package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.common.component.Chip
import com.greencom.android.podcasts2.ui.common.previewparameter.SelectableTrendingCategoriesParameterProvider
import com.greencom.android.podcasts2.ui.screen.discover.model.SelectableTrendingCategory
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun TrendingCategorySelector(
    categories: List<SelectableTrendingCategory>,
    onCategoryClicked: (category: SelectableTrendingCategory) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = categories,
            key = { it.category.id },
        ) { category ->
            Chip(
                isSelected = category.isSelected,
                onSelectedChanged = { onCategoryClicked(category) },
            ) {
                val textColor = if (category.isSelected) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialTheme.colors.onSurface
                }
                Text(
                    text = category.category.displayName,
                    color = textColor,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(SelectableTrendingCategoriesParameterProvider::class)
    categories: List<SelectableTrendingCategory>,
) {
    PodcastsComposeTheme {
        Surface {
            TrendingCategorySelector(
                categories = categories,
                onCategoryClicked = {},
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
    @PreviewParameter(SelectableTrendingCategoriesParameterProvider::class)
    categories: List<SelectableTrendingCategory>,
) {
    PodcastsComposeTheme {
        Surface {
            TrendingCategorySelector(
                categories = categories,
                onCategoryClicked = {},
            )
        }
    }
}
