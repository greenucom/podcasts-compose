package com.greencom.android.podcasts2.data.category.local

import android.content.Context
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.category.TrendingCategory
import javax.inject.Inject

class TrendingCategoryFactory @Inject constructor() {

    fun getTrendingCategories(context: Context): List<TrendingCategory> {
        return listOf(
            TrendingCategory(
                id = 55,
                name = "News",
                displayName = context.getString(R.string.news),
            ),
            TrendingCategory(
                id = 59,
                name = "Politics",
                displayName = context.getString(R.string.politics),
            ),
            TrendingCategory(
                id = 77,
                name = "Society",
                displayName = context.getString(R.string.society),
            ),
            TrendingCategory(
                id = 78,
                name = "Culture",
                displayName = context.getString(R.string.culture),
            ),
            TrendingCategory(
                id = 28,
                name = "History",
                displayName = context.getString(R.string.history),
            ),
            TrendingCategory(
                id = 103,
                name = "True Crime",
                displayName = context.getString(R.string.true_crime),
            ),
            TrendingCategory(
                id = 84,
                name = "Travel",
                displayName = context.getString(R.string.travel),
            ),
            TrendingCategory(
                id = 16,
                name = "Comedy",
                displayName = context.getString(R.string.comedy),
            ),
            TrendingCategory(
                id = 20,
                name = "Education",
                displayName = context.getString(R.string.education),
            ),
            TrendingCategory(
                id = 67,
                name = "Science",
                displayName = context.getString(R.string.science),
            ),
            TrendingCategory(
                id = 74,
                name = "Nature",
                displayName = context.getString(R.string.nature),
            ),
            TrendingCategory(
                id = 102,
                name = "Technology",
                displayName = context.getString(R.string.technology),
            ),
            TrendingCategory(
                id = 1,
                name = "Arts",
                displayName = context.getString(R.string.arts),
            ),
            TrendingCategory(
                id = 105,
                name = "Film",
                displayName = context.getString(R.string.film),
            ),
            TrendingCategory(
                id = 2,
                name = "Books",
                displayName = context.getString(R.string.books),
            ),
            TrendingCategory(
                id = 53,
                name = "Music",
                displayName = context.getString(R.string.music),
            ),
            TrendingCategory(
                id = 86,
                name = "Sports",
                displayName = context.getString(R.string.sports),
            ),
            TrendingCategory(
                id = 29,
                name = "Health",
                displayName = context.getString(R.string.health),
            ),
            TrendingCategory(
                id = 9,
                name = "Business",
                displayName = context.getString(R.string.business),
            ),
            TrendingCategory(
                id = 12,
                name = "Investing",
                displayName = context.getString(R.string.investing),
            ),
        )
    }

}
