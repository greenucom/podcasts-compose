package com.greencom.android.podcasts2.ui.screens.discover.category

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.categories.TrendingCategory
import com.greencom.android.podcasts2.ui.common.components.LoadingScreen
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun DiscoverCategoryScreen(
    modifier: Modifier = Modifier,
    category: TrendingCategory,
    pageViewModel: DiscoverCategoryPageViewModel = hiltViewModel(),
) {
    Surface(modifier = modifier) {
        LaunchedEffect(Unit) {
            pageViewModel.loadTrendingPodcasts(category)
        }

        val viewState by pageViewModel.viewState.collectAsState()
        viewState.let { state ->
            when (state) {
                DiscoverCategoryPageViewModel.ViewState.InitialLoading -> {
                    LoadingScreen(text = stringResource(R.string.loading_podcasts))
                }

                is DiscoverCategoryPageViewModel.ViewState.TrendingPodcasts -> {

                }

                DiscoverCategoryPageViewModel.ViewState.Error -> {}
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        DiscoverCategoryScreen(category = TrendingCategory.News)
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
        DiscoverCategoryScreen(category = TrendingCategory.News)
    }
}
