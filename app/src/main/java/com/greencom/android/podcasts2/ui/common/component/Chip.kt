package com.greencom.android.podcasts2.ui.common.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Chip(
    selected: Boolean,
    onSelectedChange: (selected: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = { onSelectedChange(!selected) },
        shape = MaterialTheme.shapes.small,
        colors = OutlinedButtonUtils.colors(selected),
        border = OutlinedButtonUtils.border(selected),
        content = content,
    )
}
