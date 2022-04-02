package com.greencom.android.podcasts2.ui.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.podcast.Podcast

class PodcastPreviewParameterProvider : PreviewParameterProvider<Podcast> {

    override val values: Sequence<Podcast>
        get() = sequenceOf(shortPodcast(), longPodcast())

    companion object {
        fun shortPodcast(): Podcast = Podcast(
            id = 1,
            title = "Что случилось",
            description = "«Что случилось» — новостной подкаст «Медузы».",
            author = "Meduza",
            imageUrl = "https://avatars.yandex.net/get-music-content/4382102/78874f1e.a.6323692-7/m1000x1000",
            categories = CategoryListPreviewParameterProvider.categories(),
            isSubscribed = false,
        )

        fun longPodcast(): Podcast = Podcast(
            id = 2,
            title = "The Big Beard Theory",
            description = """
                Cамое научно-космическое русскоязычное аудиошоу. Физика, математика, астрономия, астрофизика, другие естественные науки и интересные гости.

                «Теория Большой Бороды» выходит каждый четверг с 2015 года.

                Автор: Антон Поздняков
            """.trimIndent(),
            author = "#BeardyCast",
            imageUrl = "https://avatars.yandex.net/get-music-content/175191/17540468.a.6408431-5/m1000x1000",
            categories = CategoryListPreviewParameterProvider.categories(),
            isSubscribed = true,
        )
    }

}
