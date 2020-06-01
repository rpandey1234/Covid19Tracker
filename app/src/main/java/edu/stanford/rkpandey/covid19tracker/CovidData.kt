package edu.stanford.rkpandey.covid19tracker

import com.google.gson.annotations.SerializedName
import java.util.*

data class CovidData(
    @SerializedName("dateChecked") val dateChecked: Date,
    @SerializedName("positiveIncrease") val positiveIncrease: Int,
    @SerializedName("negativeIncrease") val negativeIncrease: Int,
    @SerializedName("deathIncrease") val deathIncrease: Int,
    @SerializedName("state") val state: String
)