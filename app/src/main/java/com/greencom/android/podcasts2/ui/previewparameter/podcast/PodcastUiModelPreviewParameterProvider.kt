package com.greencom.android.podcasts2.ui.previewparameter.podcast

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.model.podcast.PodcastUiModel

class PodcastUiModelPreviewParameterProvider : PreviewParameterProvider<PodcastUiModel> {

    override val values: Sequence<PodcastUiModel>
        get() = sequenceOf(theBigBeardTheory(), tekstNedeli())

    private fun theBigBeardTheory(): PodcastUiModel = PodcastUiModel(
        id = 1,
        title = "The Big Beard Theory",
        description = """
            Cамое научно-космическое аудиошоу. Физика, математика, астрономия, астрофизика, другие естественные науки и интересные гости.

            «Теория Большой Бороды» выходит каждый четверг с 2015 года.

            Автор: Антон Поздняков
        """.trimIndent(),
        author = "#BeardyCast",
        imageUrl = "https://beardycast.com/wp-content/uploads/2018/11/borodigest-tbbt.jpg",
        categories = listOf(
            CategoryUiModel(id = 67, name = "Science"),
            CategoryUiModel(id = 75, name = "Physics"),
            CategoryUiModel(id = 68, name = "Astronomy"),
        ),
        isUserSubscribed = true,
    )

    private fun tekstNedeli(): PodcastUiModel = PodcastUiModel(
        id = 2,
        title = "Текст недели",
        description = "В этом подкасте обсуждается самый заметный текст «Медузы» за неделю. Тут можно услышать голоса героев, беседу с автором, подробности, не вошедшие в текст, и специально написанную для этой истории музыку.",
        author = "Meduza",
        imageUrl = "https://is5-ssl.mzstatic.com/image/thumb/Podcasts115/v4/cf/e9/fd/cfe9fde2-dd5c-269e-7156-99987c5aaa53/mza_5046606765607931942.png/600x600bb.jpg",
        categories = listOf(
            CategoryUiModel(id = 77, name = "Society"),
            CategoryUiModel(id = 55, name = "News"),
        ),
        isUserSubscribed = false,
    )

}
