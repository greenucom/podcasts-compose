package com.greencom.android.podcasts2.data.category.local

import android.content.Context
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.category.Category
import javax.inject.Inject

class TrendingCategoryFactory @Inject constructor() {

    fun getTrendingCategories(context: Context): List<Category> {
        return listOf(
            Category(
                id = 55,
                name = "News",
                displayName = context.getString(R.string.news),
            ),
            Category(
                id = 59,
                name = "Politics",
                displayName = context.getString(R.string.politics),
            ),
            Category(
                id = 77,
                name = "Society",
                displayName = context.getString(R.string.society),
            ),
            Category(
                id = 78,
                name = "Culture",
                displayName = context.getString(R.string.culture),
            ),
            Category(
                id = 28,
                name = "History",
                displayName = context.getString(R.string.history),
            ),
            Category(
                id = 103,
                name = "True Crime",
                displayName = context.getString(R.string.true_crime),
            ),
            Category(
                id = 84,
                name = "Travel",
                displayName = context.getString(R.string.travel),
            ),
            Category(
                id = 16,
                name = "Comedy",
                displayName = context.getString(R.string.comedy),
            ),
            Category(
                id = 20,
                name = "Education",
                displayName = context.getString(R.string.education),
            ),
            Category(
                id = 67,
                name = "Science",
                displayName = context.getString(R.string.science),
            ),
            Category(
                id = 74,
                name = "Nature",
                displayName = context.getString(R.string.nature),
            ),
            Category(
                id = 102,
                name = "Technology",
                displayName = context.getString(R.string.technology),
            ),
            Category(
                id = 1,
                name = "Arts",
                displayName = context.getString(R.string.arts),
            ),
            Category(
                id = 105,
                name = "Film",
                displayName = context.getString(R.string.film),
            ),
            Category(
                id = 2,
                name = "Books",
                displayName = context.getString(R.string.books),
            ),
            Category(
                id = 53,
                name = "Music",
                displayName = context.getString(R.string.music),
            ),
            Category(
                id = 86,
                name = "Sports",
                displayName = context.getString(R.string.sports),
            ),
            Category(
                id = 29,
                name = "Health",
                displayName = context.getString(R.string.health),
            ),
            Category(
                id = 9,
                name = "Business",
                displayName = context.getString(R.string.business),
            ),
            Category(
                id = 12,
                name = "Investing",
                displayName = context.getString(R.string.investing),
            ),
        )
    }

}
