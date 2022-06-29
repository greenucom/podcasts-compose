package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.component.PodcastsFilterChip
import com.greencom.android.podcasts2.ui.model.category.CategoryUiHelper
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.previewparameter.category.CategoryUiModelListPreviewParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme

private const val ContentTypePodcastsFilterChip = "ContentTypePodcastsFilterChip"

@Composable
fun TrendingCategorySelector(
    selectableTrendingCategories: List<SelectableItem<CategoryUiModel>>,
    onCategoryClicked: (category: CategoryUiModel) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {

    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        items(
            items = selectableTrendingCategories,
            key = { it.item.id },
            contentType = { ContentTypePodcastsFilterChip },
        ) { selectableCategory ->
            val category = selectableCategory.item
            val context = LocalContext.current
            val displayName = CategoryUiHelper.getCategoryDisplayName(category, context)

            if (displayName != null) {
                PodcastsFilterChip(
                    text = displayName,
                    isSelected = selectableCategory.isSelected,
                    onSelectedChanged = { onCategoryClicked(category) },
                )
            }
        }
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview(
    @PreviewParameter(CategoryUiModelListPreviewParameterProvider::class)
    categories: List<CategoryUiModel>,
) {
    val selectableCategories = categories.map {
        SelectableItem(item = it, isSelected = it.id % 2 != 0)
    }

    PodcastsTheme {
        Surface {
            TrendingCategorySelector(
                contentPadding = PaddingValues(16.dp),
                selectableTrendingCategories = selectableCategories,
                onCategoryClicked = {},
            )
        }
    }
}
