package com.greencom.android.podcasts2.data.category.local

import android.content.Context
import android.content.res.Resources
import com.greencom.android.podcasts2.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryDisplayNameResolver @Inject constructor() {

    companion object {
        private const val UNKNOWN_RES_ID = -1
        private const val UNKNOWN_CATEGORY = ""
    }

    private val categoryIdToDisplayNameResId = createIdToDisplayNameResIdMap()

    fun getDisplayName(categoryId: Int, context: Context): String {
        val resId = categoryIdToDisplayNameResId[categoryId] ?: UNKNOWN_RES_ID
        return try {
            context.getString(resId)
        } catch (e: Resources.NotFoundException) {
            UNKNOWN_CATEGORY
        }
    }

    private fun createIdToDisplayNameResIdMap(): Map<Int, Int> {
        return hashMapOf(
            1 to R.string.arts,
            2 to R.string.books,
            3 to R.string.design,
            4 to R.string.fashion,
            5 to R.string.beauty,
            6 to R.string.food,
            7 to R.string.performing,
            8 to R.string.visual,
            9 to R.string.business,
            10 to R.string.careers,
            11 to R.string.entrepreneurship,
            12 to R.string.investing,
            13 to R.string.management,
            14 to R.string.marketing,
            15 to R.string.non_profit,
            16 to R.string.comedy,
            17 to R.string.interviews,
            18 to R.string.improv,
            19 to R.string.stand_up,
            20 to R.string.education,
            21 to R.string.courses,
            22 to R.string.how_to,
            23 to R.string.language,
            24 to R.string.learning,
            25 to R.string.self_improvement,
            26 to R.string.fiction,
            27 to R.string.drama,
            28 to R.string.history,
            29 to R.string.health,
            30 to R.string.fitness,
            31 to R.string.alternative,
            32 to R.string.medicine,
            33 to R.string.mental,
            34 to R.string.nutrition,
            35 to R.string.sexuality,
            36 to R.string.kids,
            37 to R.string.family,
            38 to R.string.parenting,
            39 to R.string.pets,
            40 to R.string.animals,
            41 to R.string.stories,
            42 to R.string.leisure,
            43 to R.string.animation,
            44 to R.string.manga,
            45 to R.string.automotive,
            46 to R.string.aviation,
            47 to R.string.crafts,
            48 to R.string.games,
            49 to R.string.hobbies,
            50 to R.string.home,
            51 to R.string.garden,
            52 to R.string.video_games,
            53 to R.string.music,
            54 to R.string.commentary,
            55 to R.string.news,
            56 to R.string.daily,
            57 to R.string.entertainment,
            58 to R.string.government,
            59 to R.string.politics,
            60 to R.string.buddhism,
            61 to R.string.christianity,
            62 to R.string.hinduism,
            63 to R.string.islam,
            64 to R.string.judaism,
            65 to R.string.religion,
            66 to R.string.spirituality,
            67 to R.string.science,
            68 to R.string.astronomy,
            69 to R.string.chemistry,
            70 to R.string.earth,
            71 to R.string.life,
            72 to R.string.mathematics,
            73 to R.string.natural,
            74 to R.string.nature,
            75 to R.string.physics,
            76 to R.string.social,
            77 to R.string.society,
            78 to R.string.culture,
            79 to R.string.documentary,
            80 to R.string.personal,
            81 to R.string.journals,
            82 to R.string.philosophy,
            83 to R.string.places,
            84 to R.string.travel,
            85 to R.string.relationships,
            86 to R.string.sports,
            87 to R.string.baseball,
            88 to R.string.basketball,
            89 to R.string.cricket,
            90 to R.string.fantasy,
            91 to R.string.football,
            92 to R.string.golf,
            93 to R.string.hockey,
            94 to R.string.rugby,
            95 to R.string.running,
            96 to R.string.soccer,
            97 to R.string.swimming,
            98 to R.string.tennis,
            99 to R.string.volleyball,
            100 to R.string.wilderness,
            101 to R.string.wrestling,
            102 to R.string.technology,
            103 to R.string.true_crime,
            104 to R.string.tv,
            105 to R.string.film,
            106 to R.string.after_shows,
            107 to R.string.reviews,
            108 to R.string.climate,
            109 to R.string.weather,
            110 to R.string.tabletop,
            111 to R.string.role_playing,
            112 to R.string.cryptocurrency,
        )
    }

}
