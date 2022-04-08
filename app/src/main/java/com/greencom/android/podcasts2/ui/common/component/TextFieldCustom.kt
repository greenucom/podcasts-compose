package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun TextFieldCustom(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(8.dp),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
) {

    val textColor by colors.textColor(enabled = enabled)
    val backgroundColor by colors.backgroundColor(enabled = enabled)
    val placeholderColor by colors.placeholderColor(enabled = enabled)
    val leadingIconColor by colors.leadingIconColor(enabled = enabled, isError = false)
    val trailingIconColor by colors.trailingIconColor(enabled = enabled, isError = false)
    val cursorColor by colors.cursorColor(isError = true)

    Surface(
        modifier = modifier.heightIn(min = ComponentDefaults.MinHeight),
        shape = shape,
        color = backgroundColor,
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            BasicTextField(
                modifier = Modifier.weight(1f),
                value = value,
                onValueChange = onValueChanged,
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle.copy(color = textColor),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                visualTransformation = visualTransformation,
                onTextLayout = onTextLayout,
                interactionSource = interactionSource,
                cursorBrush = SolidColor(cursorColor),
                decorationBox = { innerTextField ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CompositionLocalProvider(LocalContentColor provides leadingIconColor) {
                            leadingIcon?.invoke()
                        }

                        Box(contentAlignment = Alignment.CenterStart) {
                            if (value.isEmpty()) {
                                CompositionLocalProvider(LocalContentColor provides placeholderColor) {
                                    placeholder?.invoke()
                                }
                            }
                            innerTextField()
                        }
                    }
                },
            )

            CompositionLocalProvider(LocalContentColor provides trailingIconColor) {
                trailingIcon?.invoke()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            TextFieldCustom(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                value = "Query",
                onValueChanged = {},
                placeholder = { Text(text = "Placeholder") },
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
            TextFieldCustom(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                value = "Query",
                onValueChanged = {},
                placeholder = { Text(text = "Placeholder") },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(end = 8.dp),
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.padding(start = 8.dp),
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                    )
                },
            )
        }
    }
}
