package com.greencom.android.podcasts2.ui.screen.search.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.searchBackground

object SearchTextFieldUtils {

    val MinHeightDp = 40.dp

    val colors: TextFieldColors
        @Composable get() = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.searchBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        )

    @Composable
    fun LeadingIcon(modifier: Modifier = Modifier) {
        Icon(
            modifier = modifier,
            imageVector = Icons.Outlined.Search,
            contentDescription = null,
        )
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun TrailingIcon(
        value: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        AnimatedContent(
            modifier = modifier,
            targetState = value.isNotEmpty(),
        ) { isNotEmpty ->
            if (isNotEmpty) {
                IconButton(onClick = onClick) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = null,
                    )
                }
            }
        }
    }

}
