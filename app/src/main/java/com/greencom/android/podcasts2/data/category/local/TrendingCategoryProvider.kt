package com.greencom.android.podcasts2.data.category.local

import com.greencom.android.podcasts2.domain.category.Category
import javax.inject.Inject

class TrendingCategoryProvider @Inject constructor() {

    fun getTrendingCategories(): List<Category> {
        return listOf(
            Category(id = 55, name = "News"),
            Category(id = 59, name = "Politics"),
            Category(id = 77, name = "Society"),
            Category(id = 78, name = "Culture"),
            Category(id = 28, name = "History"),
            Category(id = 103, name = "True Crime"),
            Category(id = 84, name = "Travel"),
            Category(id = 16, name = "Comedy"),
            Category(id = 20, name = "Education"),
            Category(id = 67, name = "Science"),
            Category(id = 74, name = "Nature"),
            Category(id = 102, name = "Technology"),
            Category(id = 1, name = "Arts"),
            Category(id = 105, name = "Film"),
            Category(id = 2, name = "Books"),
            Category(id = 53, name = "Music"),
            Category(id = 86, name = "Sports"),
            Category(id = 29, name = "Health"),
            Category(id = 9, name = "Business"),
            Category(id = 12, name = "Investing"),
        )
    }

}
