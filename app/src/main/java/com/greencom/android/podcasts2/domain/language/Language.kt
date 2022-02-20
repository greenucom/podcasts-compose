package com.greencom.android.podcasts2.domain.language

sealed class Language(val isoCode: String) {

    object En : Language("en")
    object Ru : Language("ru")

}

fun List<Language>.toLanguagesString(): String = this.joinToString(",") { it.isoCode }
