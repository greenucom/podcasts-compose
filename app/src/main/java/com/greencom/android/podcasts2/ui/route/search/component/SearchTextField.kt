package com.greencom.android.podcasts2.ui.route.search.component

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.PodcastsIcons
import com.greencom.android.podcasts2.ui.common.TextFieldCustom
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme
import com.greencom.android.podcasts2.ui.theme.searchBackground

private const val TextAlpha = 0.87f
private const val IconAlpha = 0.74f

private val IconSize = 40.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    onClearTextFieldClicked: () -> Unit,
    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colors.searchBackground,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.padding(start = 16.dp),
                imageVector = PodcastsIcons.Search,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface.copy(alpha = IconAlpha),
            )

            val textColor = MaterialTheme.colors.onSurface.copy(alpha = TextAlpha)
            TextFieldCustom(
                modifier = Modifier.weight(1f),
                value = value,
                onValueChange = onValueChanged,
                placeholder = { Text(text = stringResource(R.string.search_for_podcasts)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions { onImeSearch() },
                singleLine = true,
                shape = MaterialTheme.shapes.small,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = textColor,
                    placeholderColor = textColor,
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                ),
                startPadding = 8.dp,
            )

            AnimatedVisibility(
                modifier = Modifier.height(IconSize),
                visible = value.isNotEmpty(),
                enter = scaleIn(spring()) + fadeIn(spring()),
                exit = scaleOut(spring(stiffness = Spring.StiffnessLow)) +
                        fadeOut(spring(stiffness = Spring.StiffnessLow)),
            ) {
                IconButton(onClick = onClearTextFieldClicked) {
                    Icon(
                        imageVector = PodcastsIcons.Close,
                        contentDescription = stringResource(R.string.content_desc_clear_text_field),
                        tint = MaterialTheme.colors.onSurface.copy(alpha = IconAlpha),
                    )
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PodcastsTheme {
        Surface {
            var value by remember { mutableStateOf("") }
            SearchTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = value,
                onValueChanged = { value = it },
                onClearTextFieldClicked = {},
                onImeSearch = {},
            )
        }
    }
}
