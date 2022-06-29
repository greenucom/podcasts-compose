package com.greencom.android.podcasts2.ui.common.component.podcast

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
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

private val CoverSize = 80.dp
private const val TitleMaxLines = 2
private const val AuthorMaxLines = 1
private const val DescriptionMaxLines = 2
private const val CategoryLabelMaxCount = 3
private const val AuthorAlpha = 0.74f

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PodcastItem(
    podcast: PodcastUiModel,
    onPodcastClicked: (PodcastUiModel) -> Unit,
    onSubscribedChanged: (PodcastUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        onClick = { onPodcastClicked(podcast) },
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
                    data = podcast.imageUrl,
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
                        modifier = Modifier.padding(top = 4.dp).alpha(AuthorAlpha),
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
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview(
    @PreviewParameter(PodcastUiModelPreviewParameterProvider::class)
    podcast: PodcastUiModel,
) {
    PodcastsTheme {
        Surface {
            PodcastItem(
                podcast = podcast,
                onPodcastClicked = {},
                onSubscribedChanged = {},
            )
        }
    }
}
