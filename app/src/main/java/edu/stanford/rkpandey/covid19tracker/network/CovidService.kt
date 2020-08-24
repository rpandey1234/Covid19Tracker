package edu.stanford.rkpandey.covid19tracker.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import edu.stanford.rkpandey.covid19tracker.models.CovidData
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://covidtracking.com/api/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface CovidService {
    @GET("us/daily.json")
    fun getNationalData(): Deferred<List<CovidData>>

    @GET("states/daily.json")
    fun getStatesData(): Deferred<List<CovidData>>
}

object Api {
    val retrofitService : CovidService by lazy { retrofit.create(CovidService::class.java) }
}