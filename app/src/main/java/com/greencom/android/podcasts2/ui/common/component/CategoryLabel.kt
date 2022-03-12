package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.ui.common.previewparameter.CategoriesParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.categoryLabelBackground

@Composable
fun CategoryLabel(
    categoryDisplayName: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.categoryLabelBackground,
        shape = MaterialTheme.shapes.small,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            text = categoryDisplayName,
            style = MaterialTheme.typography.caption,
            color = Color.Black,
        )
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
            CategoryLabel(categoryDisplayName = categories[0].displayName)
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
            CategoryLabel(categoryDisplayName = categories[0].displayName)
        }
    }
}
