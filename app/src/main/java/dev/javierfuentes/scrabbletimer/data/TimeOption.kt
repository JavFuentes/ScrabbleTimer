package dev.javierfuentes.scrabbletimer.data

import androidx.annotation.StringRes
import dev.javierfuentes.scrabbletimer.R

data class TimeOption(
    val minutes: Int,
    @param:StringRes val displayTextRes: Int,
    @param:StringRes val descriptionRes: Int
) {
    companion object {
        fun getTimeOptions(): List<TimeOption> = listOf(
            TimeOption(1, R.string.time_1_min, R.string.time_1_min_desc),
            TimeOption(2, R.string.time_2_min, R.string.time_2_min_desc),
            TimeOption(3, R.string.time_3_min, R.string.time_3_min_desc),
            TimeOption(4, R.string.time_4_min, R.string.time_4_min_desc),
            TimeOption(5, R.string.time_5_min, R.string.time_5_min_desc)
        )
    }
}