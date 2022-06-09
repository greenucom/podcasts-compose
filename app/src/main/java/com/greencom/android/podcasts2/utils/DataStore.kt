package com.greencom.android.podcasts2.utils

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val DATA_STORE_PREFERENCES_NAME = "data_store_preferences"

val Context.dataStorePreferences by preferencesDataStore(DATA_STORE_PREFERENCES_NAME)
