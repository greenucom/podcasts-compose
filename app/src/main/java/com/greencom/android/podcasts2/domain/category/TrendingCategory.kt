package com.greencom.android.podcasts2.domain.category

import androidx.annotation.StringRes
import com.greencom.android.podcasts2.R

sealed class TrendingCategory(
    id: Int,
    name: String,
    @StringRes val displayNameResId: Int,
) : ICategory(id, name) {

    object News : TrendingCategory(55, "News", R.string.news)
    object Politics : TrendingCategory(59, "Politics", R.string.politics)
    object Society : TrendingCategory(77, "Society", R.string.society)
    object History : TrendingCategory(28, "History", R.string.history)
    object Education : TrendingCategory(20, "Education", R.string.education)
    object Science : TrendingCategory(67, "Science", R.string.science)
    object Technology : TrendingCategory(102, "Technology", R.string.technology)
    object Sports : TrendingCategory(86, "Sports", R.string.sports)

    companion object {

        val defaultCategories = listOf(
            News, Politics, Society, History, Education, Science, Technology, Sports,
        )

    }

}
