package com.greencom.android.podcasts2.ui.screens.discover.components

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun SearchBar(
    value: String,
    onValueChange: (value: String) -> Unit,
    onImeSearch: (value: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val textFieldColors = if (isSystemInDarkTheme()) {
        val color = MaterialTheme.colors.onSurface.copy(
            alpha = SearchBarUtils.DarkBackgroundColorAlpha
        )
        TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = color,
            focusedBorderColor = color,
            unfocusedBorderColor = color,
            disabledBorderColor = color,
            errorBorderColor = color,
        )
    } else {
        TextFieldDefaults.outlinedTextFieldColors()
    }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = RoundedCornerShape(percent = SearchBarUtils.CornerPercent),
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
            )
        },
        colors = textFieldColors,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = { onImeSearch(value) }
        ),
    )
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            SearchBar(
                modifier = Modifier.padding(16.dp),
                value = "The big beard",
                onValueChange = {},
                onImeSearch = {},
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
private fun Dark() {
    PodcastsComposeTheme {
        Surface {
            SearchBar(
                modifier = Modifier.padding(16.dp),
                value = "The big beard",
                onValueChange = {},
                onImeSearch = {},
            )
        }
    }
}
