package com.greencom.android.podcasts2.ui.common.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Chip(
    isSelected: Boolean,
    onSelectedChanged: (selected: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = { onSelectedChanged(!isSelected) },
        shape = MaterialTheme.shapes.small,
        colors = OutlinedButtonUtils.colors(isSelected),
        border = OutlinedButtonUtils.border(isSelected),
        content = content,
    )
}
