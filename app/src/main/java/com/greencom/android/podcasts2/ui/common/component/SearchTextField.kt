package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.searchBackground

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchTextField(
    query: String,
    onQueryChanged: (String) -> Unit,
    placeholderText: String,
    onImeSearch: () -> Unit,
    onClearQuery: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val textStyle = MaterialTheme.typography.body1.copy(
        color = MaterialTheme.colors.onSurface.copy(alpha = ComponentDefaults.TextAlpha)
    )

    val placeholder = @Composable {
        Text(
            text = placeholderText,
            style = textStyle,
        )
    }

    val leadingIcon = @Composable {
        Icon(
            modifier = Modifier
                .padding(end = 8.dp)
                .alpha(ComponentDefaults.IconAlpha),
            imageVector = Icons.Outlined.Search,
            contentDescription = placeholderText,
        )
    }

    val trailingIcon = @Composable {
        AnimatedVisibility(
            visible = query.isNotEmpty(),
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut(),
        ) {

            IconButton(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp),
                onClick = onClearQuery,
            ) {

                Icon(
                    modifier = Modifier.alpha(ComponentDefaults.IconAlpha),
                    imageVector = Icons.Outlined.Close,
                    contentDescription = stringResource(R.string.clear_search_field),
                )
            }
        }
    }

    val iconColor = MaterialTheme.colors.onSurface
    val colors = TextFieldDefaults.textFieldColors(
        textColor = MaterialTheme.colors.onSurface.copy(alpha = ComponentDefaults.TextAlpha),
        backgroundColor = MaterialTheme.colors.searchBackground,
        leadingIconColor = iconColor,
        trailingIconColor = iconColor,
    )

    TextFieldCustom(
        modifier = modifier,
        value = query,
        onValueChanged = onQueryChanged,
        textStyle = textStyle,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onImeSearch() }),
        shape = MaterialTheme.shapes.small,
        colors = colors,
    )
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        var query by remember { mutableStateOf("") }
        Surface {
            SearchTextField(
                modifier = Modifier.padding(16.dp),
                query = query,
                onQueryChanged = { query = it },
                placeholderText = "Search for podcasts",
                onImeSearch = {},
                onClearQuery = {},
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
        var query by remember { mutableStateOf("") }
        Surface {
            SearchTextField(
                modifier = Modifier.padding(16.dp),
                query = query,
                onQueryChanged = { query = it },
                placeholderText = "Search for podcasts",
                onImeSearch = {},
                onClearQuery = {},
            )
        }
    }
}
