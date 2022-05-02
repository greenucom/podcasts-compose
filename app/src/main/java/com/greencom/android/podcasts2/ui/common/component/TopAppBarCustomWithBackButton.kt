package com.greencom.android.podcasts2.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
import com.greencom.android.podcasts2.R

@Composable
fun TopAppBarCustomWithBackButton(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)? = null,
) {

    Column(modifier = modifier) {

        Spacer(
            Modifier
                .statusBarsHeight()
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.surface)
        )

        val backIconButton = @Composable {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = stringResource(R.string.go_back),
                )
            }
        }

        TopAppBar(
            title = { title?.invoke() },
            navigationIcon = backIconButton,
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 0.dp,
        )

    }
}
