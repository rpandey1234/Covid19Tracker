package edu.stanford.rkpandey.covid19tracker.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class CovidData(
    @SerializedName("dateChecked")
    @Expose
    val dateChecked: Date,
    @SerializedName("positiveIncrease")
    @Expose
    val positiveIncrease: Int,
    @SerializedName("negativeIncrease")
    @Expose
    val negativeIncrease: Int,
    @SerializedName("deathIncrease")
    @Expose
    val deathIncrease: Int,
    @SerializedName("state")
    @Expose
    val state: String
) : Parcelable