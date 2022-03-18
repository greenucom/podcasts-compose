package com.greencom.android.podcasts2.data.category

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.greencom.android.podcasts2.data.category.local.TrendingCategoryProvider
import com.greencom.android.podcasts2.domain.category.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class CategoryLocalDataSource @Inject constructor(
    private val trendingCategoryProvider: TrendingCategoryProvider,
    private val dataStore: DataStore<Preferences>,
) {

    fun getTrendingCategories(): List<Category> {
        return trendingCategoryProvider.getTrendingCategories()
    }

    private val SELECTED_TRENDING_CATEGORIES_IDS_KEY = stringPreferencesKey(
        SELECTED_TRENDING_CATEGORIES_IDS_KEY_NAME
    )

    fun getSelectedTrendingCategoriesIds(): Flow<Set<Int>> = dataStore.data
        .catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }
        .map { preferences ->
            val string = preferences[SELECTED_TRENDING_CATEGORIES_IDS_KEY]
            selectedTrendingCategoriesIdsStringToMutableSet(string)
        }
        .distinctUntilChanged()

    suspend fun toggleSelectedTrendingCategoryId(id: Int) {
        dataStore.edit { preferences ->
            val string = preferences[SELECTED_TRENDING_CATEGORIES_IDS_KEY]
            val set = selectedTrendingCategoriesIdsStringToMutableSet(string)
            if (id in set) set.remove(id) else set.add(id)
            preferences[SELECTED_TRENDING_CATEGORIES_IDS_KEY] = set.joinToString(SEPARATOR)
        }
    }

    private fun selectedTrendingCategoriesIdsStringToMutableSet(string: String?): MutableSet<Int> {
        return try {
            string
                ?.split(SEPARATOR)
                ?.map { it.toInt() }
                ?.toMutableSet()
                ?: mutableSetOf()
        } catch (e: NumberFormatException) {
            mutableSetOf()
        }
    }

    companion object {

        private const val SELECTED_TRENDING_CATEGORIES_IDS_KEY_NAME =
            "selected_trending_categories_ids"

        private const val SEPARATOR = ","

    }

}
