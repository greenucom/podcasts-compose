package com.greencom.android.podcasts2.ui.common.component.podcast

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.PodcastsAsyncImage
import com.greencom.android.podcasts2.ui.common.component.category.CategoryLabelRow
import com.greencom.android.podcasts2.ui.model.podcast.PodcastUiModel
import com.greencom.android.podcasts2.ui.previewparameter.podcast.PodcastUiModelPreviewParameterProvider
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil
import com.greencom.android.podcasts2.ui.theme.placeholder

private val CoverSize = 80.dp
private const val TitleMaxLines = 2
private const val AuthorMaxLines = 1
private const val DescriptionMaxLines = 2
private const val CategoryLabelMaxCount = 3
private const val AuthorAlpha = 0.74f

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PodcastItem(
    podcast: PodcastUiModel,
    onPodcastClicked: (PodcastUiModel) -> Unit,
    onIsUserSubscribedChanged: (PodcastUiModel) -> Unit,
    modifier: Modifier = Modifier,
    onPodcastLongClicked: (PodcastUiModel) -> Unit = {},
) {
    Surface(
        modifier = modifier.combinedClickable(
            onClick = { onPodcastClicked(podcast) },
            onLongClick = { onPodcastLongClicked(podcast) }
        ),
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 12.dp),
        ) {

            Row {
                PodcastsAsyncImage(
                    modifier = Modifier
                        .size(CoverSize)
                        .clip(MaterialTheme.shapes.medium),
                    url = podcast.imageUrl,
                    contentDescription = stringResource(id = R.string.content_desc_podcast_cover),
                )

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = podcast.title,
                        style = MaterialTheme.typography.h6,
                        maxLines = TitleMaxLines,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .alpha(AuthorAlpha),
                        text = podcast.author,
                        style = MaterialTheme.typography.caption,
                        maxLines = AuthorMaxLines,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = podcast.description,
                style = MaterialTheme.typography.body2,
                maxLines = DescriptionMaxLines,
                overflow = TextOverflow.Ellipsis,
            )

            CategoryLabelRow(
                modifier = Modifier.padding(top = 8.dp),
                categories = podcast.categories,
                maxCount = CategoryLabelMaxCount,
            )

            SubscribeButton(
                modifier = Modifier.padding(top = 6.dp),
                isUserSubscribed = podcast.isUserSubscribed,
                onIsUserSubscribedChanged = {
                    val newPodcast = podcast.copy(isUserSubscribed = it)
                    onIsUserSubscribedChanged(newPodcast)
                },
            )
        }
    }
}

@Composable
fun PodcastItemPlaceholder(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row {
                Box(
                    modifier = Modifier
                        .size(CoverSize)
                        .clip(shape = MaterialTheme.shapes.medium)
                        .drawBehind { drawRect(color = color) }
                )

                Column(modifier = Modifier.padding(start = 16.dp)) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(24.dp)
                            .clip(shape = MaterialTheme.shapes.placeholder)
                            .drawBehind { drawRect(color = color) }
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(0.5f)
                            .height(12.dp)
                            .clip(shape = MaterialTheme.shapes.placeholder)
                            .drawBehind { drawRect(color = color) }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .height(16.dp)
                    .clip(shape = MaterialTheme.shapes.placeholder)
                    .drawBehind { drawRect(color = color) }
            )

            Box(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth(0.4f)
                    .height(16.dp)
                    .clip(shape = MaterialTheme.shapes.placeholder)
                    .drawBehind { drawRect(color = color) }
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PodcastItemPreview(
    @PreviewParameter(PodcastUiModelPreviewParameterProvider::class)
    podcast: PodcastUiModel,
) {
    PodcastsTheme {
        Surface {
            PodcastItem(
                podcast = podcast,
                onPodcastClicked = {},
                onIsUserSubscribedChanged = {},
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PodcastItemPlaceholderPreview() {
    PodcastsTheme {
        Surface {
            PodcastItemPlaceholder(
                color = MaterialTheme.colors.onSurfaceUtil,
            )
        }
    }
}
