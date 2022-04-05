package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private val MinHeight = 40.dp

@Composable
fun TextFieldCustom(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    shape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color = MaterialTheme.colors.surface,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current.copy(
        color = MaterialTheme.colors.onSurface,
    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val textStyle = textStyle.copy(
        color = textStyle.color.copy(
            alpha = LocalContentAlpha.current,
        )
    )

    BasicTextField(
        modifier = modifier
            .heightIn(min = MinHeight)
            .background(
                color = backgroundColor,
                shape = shape,
            ),
        value = value,
        onValueChange = onValueChanged,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                leadingIcon?.invoke()

                Box(contentAlignment = Alignment.CenterStart) {
                    if (value.isEmpty()) {
                        placeholder?.invoke()
                    }
                    innerTextField()
                }

                trailingIcon?.invoke()
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            TextFieldCustom(
                modifier = Modifier.padding(16.dp),
                value = "Query",
                onValueChanged = {},
                placeholder = { Text(text = "Placeholder") },
                backgroundColor = MaterialTheme.colors.onSurfaceUtil,
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
                modifier = Modifier.padding(16.dp),
                value = "Query",
                onValueChanged = {},
                placeholder = { Text(text = "Placeholder") },
                backgroundColor = MaterialTheme.colors.onSurfaceUtil,
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

@Composable
@Preview(showBackground = true)
private fun RoundLight() {
    PodcastsComposeTheme {
        Surface {
            TextFieldCustom(
                modifier = Modifier.padding(16.dp),
                value = "Query",
                onValueChanged = {},
                placeholder = { Text(text = "Placeholder") },
                shape = MaterialTheme.shapes.small,
                backgroundColor = MaterialTheme.colors.onSurfaceUtil,
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

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun RoundDark() {
    PodcastsComposeTheme {
        Surface {
            TextFieldCustom(
                modifier = Modifier.padding(16.dp),
                value = "Query",
                onValueChanged = {},
                placeholder = { Text(text = "Placeholder") },
                shape = MaterialTheme.shapes.small,
                backgroundColor = MaterialTheme.colors.onSurfaceUtil,
            )
        }
    }
}
