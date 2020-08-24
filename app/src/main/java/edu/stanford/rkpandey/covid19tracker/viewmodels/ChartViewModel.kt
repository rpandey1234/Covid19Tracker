package edu.stanford.rkpandey.covid19tracker.viewmodels

import android.util.Log
import androidx.lifecycle.*
import edu.stanford.rkpandey.covid19tracker.models.CovidData
import edu.stanford.rkpandey.covid19tracker.network.Api
import kotlinx.coroutines.launch
import retrofit2.await

enum class ApiStatus { LOADING, ERROR, DONE, NONE }

class ChartViewModel() : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status

    private val _nationalData = MutableLiveData<List<CovidData>>()
    val nationalData: LiveData<List<CovidData>>
        get() = _nationalData

    private val _statesData = MutableLiveData<List<CovidData>>()
    val statesData: LiveData<List<CovidData>>
        get() = _statesData

    private val listData = ArrayList<CovidData>()

    init {
        fetchNationalData()
        fetchStatesData()
    }

    private fun fetchNationalData(){
        viewModelScope.launch {
            val getPropertiesDeferred =  Api.retrofitService.getNationalData()
            try {
                _status.value = ApiStatus.LOADING
                val listResult = getPropertiesDeferred.await()

                if (listResult.isNotEmpty()){
                    _nationalData.value = listResult
                    _status.value = ApiStatus.DONE
                }
                else{
                    _nationalData.value = ArrayList()
                    _status.value = ApiStatus.NONE
                }
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                Log.e("error", e.toString())
                _nationalData.value = ArrayList()
            }
        }
    }

    private fun fetchStatesData(){
        viewModelScope.launch {
            val getPropertiesDeferred =  Api.retrofitService.getStatesData()
            try {
                _status.value = ApiStatus.LOADING
                val listResult = getPropertiesDeferred.await()

                if (listResult.isNotEmpty()){
                    listResult.forEach {
                        if (it.dateChecked != null){
                            listData.add(it)
                        }
                    }

                    _statesData.value = listData.toList()
                    _status.value = ApiStatus.DONE
                }
                else{
                    _status.value = ApiStatus.NONE
                }
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                Log.e("error", e.toString())
                _statesData.value = ArrayList()
            }
        }
    }
}