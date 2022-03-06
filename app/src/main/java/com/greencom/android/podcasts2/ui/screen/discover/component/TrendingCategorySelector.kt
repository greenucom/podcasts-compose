package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.domain.category.TrendingCategory
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.component.FilterChip
import com.greencom.android.podcasts2.ui.screen.discover.previewparameter.SelectableTrendingCategoriesParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun TrendingCategorySelector(
    selectableCategories: List<SelectableItem<TrendingCategory>>,
    onSelectableCategoryClicked: (selectableCategory: SelectableItem<TrendingCategory>) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Surface(modifier = modifier) {
        LazyRow(
            contentPadding = contentPadding,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = selectableCategories,
                key = { it.item.id },
            ) { selectableCategory ->
                FilterChip(
                    isSelected = selectableCategory.isSelected,
                    onSelectedChanged = { onSelectableCategoryClicked(selectableCategory) },
                    text = selectableCategory.item.displayName,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light(
    @PreviewParameter(SelectableTrendingCategoriesParameterProvider::class)
    selectableCategories: List<SelectableItem<TrendingCategory>>
) {
    PodcastsComposeTheme {
        Surface {
            TrendingCategorySelector(
                selectableCategories = selectableCategories,
                onSelectableCategoryClicked = {},
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
    selectableCategories: List<SelectableItem<TrendingCategory>>
) {
    PodcastsComposeTheme {
        Surface {
            TrendingCategorySelector(
                selectableCategories = selectableCategories,
                onSelectableCategoryClicked = {},
            )
        }
    }
}
