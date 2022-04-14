package com.greencom.android.podcasts2.ui.common

import android.content.Context
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.episode.Episode

object EpisodeHelper {

    fun getFormattedNumber(episode: Episode, context: Context): String {
        return context.getString(
            R.string.season_d_episode_d,
            episode.seasonNumber,
            episode.episodeNumber,
        )
    }

}
