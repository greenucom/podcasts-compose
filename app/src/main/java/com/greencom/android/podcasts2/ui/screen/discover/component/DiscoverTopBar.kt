package com.greencom.android.podcasts2.ui.screen.discover.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight

@Composable
fun DiscoverTopBar(
    onSearchClicked: () -> Unit,
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
            SearchButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = onSearchClicked,
            )
        }
    }
}
