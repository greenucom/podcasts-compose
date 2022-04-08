package com.greencom.android.podcasts2.ui.screen.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.component.SearchTextField

@Composable
fun SearchTopBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onImeSearch: (String) -> Unit,
    onClearQuery: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        Spacer(
            Modifier
                .statusBarsHeight()
                .background(color = MaterialTheme.colors.surface)
        )

        TopAppBar(
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 0.dp,
            contentPadding = PaddingValues(0.dp),
        ) {
            SearchTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                query = query,
                onQueryChanged = onQueryChanged,
                placeholderText = stringResource(R.string.search_for_podcasts),
                onImeSearch = onImeSearch,
                onClearQuery = onClearQuery,
            )
        }
    }
}
