package com.greencom.android.podcasts2.data.category

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.greencom.android.podcasts2.data.category.local.TrendingCategoriesProvider
import com.greencom.android.podcasts2.domain.category.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class CategoryLocalDataSource @Inject constructor(
    private val dataStorePreferences: DataStore<Preferences>,
    private val trendingCategoriesProvider: TrendingCategoriesProvider,
) {

    fun getTrendingCategories(): List<Category> {
        return trendingCategoriesProvider.getTrendingCategories()
    }

    fun getSelectedTrendingCategoriesIds(): Flow<Set<Int>> = dataStorePreferences.data
        .catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }
        .map { preferences ->
            val string = preferences[selectedTrendingCategoriesIdsKey]
            selectedTrendingCategoriesIdsStringToMutableSet(string)
        }

    suspend fun toggleTrendingCategory(category: Category) {
        dataStorePreferences.edit { preferences ->
            val string = preferences[selectedTrendingCategoriesIdsKey]
            val set = selectedTrendingCategoriesIdsStringToMutableSet(string)
            val id = category.id
            if (id in set) set.remove(id) else set.add(id)
            preferences[selectedTrendingCategoriesIdsKey] = set.joinToString(Separator)
        }
    }

    private fun selectedTrendingCategoriesIdsStringToMutableSet(string: String?): MutableSet<Int> {
        return try {
            string
                ?.split(Separator)
                ?.map { it.toInt() }
                ?.toMutableSet()
                ?: mutableSetOf()
        } catch (e: NumberFormatException) {
            mutableSetOf()
        }
    }

    private val selectedTrendingCategoriesIdsKey = stringPreferencesKey(
        SelectedTrendingCategoriesIdsKeyName
    )

    companion object {
        private const val SelectedTrendingCategoriesIdsKeyName = "selected_trending_categories_ids"
        private const val Separator = ","
    }

}
