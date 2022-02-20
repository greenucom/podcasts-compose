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
    object TrueCrime : TrendingCategory(103, "True Crime", R.string.true_crime)
    object Education : TrendingCategory(20, "Education", R.string.education)
    object Science : TrendingCategory(67, "Science", R.string.science)
    object Technology : TrendingCategory(102, "Technology", R.string.technology)
    object Arts : TrendingCategory(1, "Arts", R.string.arts)
    object Sports : TrendingCategory(86, "Sports", R.string.sports)
    object Health : TrendingCategory(29, "Health", R.string.health)
    object Business : TrendingCategory(9, "Business", R.string.business)
    object Investing : TrendingCategory(12, "Investing", R.string.investing)

    companion object {

        val categories = listOf(
            News, Politics, Society, History, TrueCrime, Education, Science, Technology,
            Arts, Sports, Health, Business, Investing,
        )

    }

}
