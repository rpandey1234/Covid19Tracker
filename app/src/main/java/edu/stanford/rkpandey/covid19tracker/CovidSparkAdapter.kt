package edu.stanford.rkpandey.covid19tracker

import com.robinhood.spark.SparkAdapter

class CovidSparkAdapter(private val dailyData: List<CovidData>) : SparkAdapter() {
    override fun getY(index: Int): Float {
        val chosenDayData = dailyData[index]
        return chosenDayData.positiveIncrease.toFloat()
    }

    override fun getItem(index: Int) = dailyData[index]

    override fun getCount() = dailyData.size
}