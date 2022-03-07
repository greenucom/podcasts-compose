package com.greencom.android.podcasts2.ui.screen.search.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun SearchTextField(
    value: String,
    onValueChanged: (value: String) -> Unit,
    onSearch: (value: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier.heightIn(SearchTextFieldUtils.MinHeightDp),
        value = value,
        onValueChange = onValueChanged,
        placeholder = { Text(stringResource(R.string.search_for_podcasts)) },
        leadingIcon = { SearchTextFieldUtils.LeadingIcon() },
        trailingIcon = {
            SearchTextFieldUtils.TrailingIcon(
                value = value,
                onClick = { onValueChanged("") },
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch(value) }),
        shape = MaterialTheme.shapes.small,
        colors = SearchTextFieldUtils.colors,
    )
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            SearchTextField(
                modifier = Modifier.padding(16.dp),
                value = "The Big Beard Theory",
                onValueChanged = {},
                onSearch = {},
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
            SearchTextField(
                modifier = Modifier.padding(16.dp),
                value = "",
                onValueChanged = {},
                onSearch = {},
            )
        }
    }
}
