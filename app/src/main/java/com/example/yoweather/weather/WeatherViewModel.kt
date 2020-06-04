package com.example.yoweather.weather


import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class WeatherViewModel : ViewModel() {

    val APPID:String="cfd2ac5e73d698cac9b7f50917968af9"
    var City:String=""


    private val _name=MutableLiveData<String>()
    val name : LiveData<String>
    get() = _name


    private var _temperature=MutableLiveData<String>()
    val temperature : LiveData<String>
    get()=_temperature

    private var _description=MutableLiveData<String>()
    val description : LiveData<String>
        get()=_description

    private var _mintemp=MutableLiveData<String>()
    val mintemp : LiveData<String>
        get()=_mintemp

    private var _maxtemp=MutableLiveData<String>()
    val maxtemp : LiveData<String>
        get()=_maxtemp

    private var _sunrise=MutableLiveData<String>()
    val sunrise : LiveData<String>
        get()=_sunrise

    private var _sunset=MutableLiveData<String>()
    val sunset : LiveData<String>
        get()=_sunset

    private var _wind=MutableLiveData<String>()
    val wind : LiveData<String>
        get()=_wind

    private var _pressure=MutableLiveData<String>()
    val pressure : LiveData<String>
        get()=_pressure

    private var _humidity=MutableLiveData<String>()
    val humidity : LiveData<String>
        get()=_humidity

    private var _response=MutableLiveData<String>()
    val response : LiveData<String>
        get()=_response




    private var viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob + Dispatchers.Main)


    @RequiresApi(Build.VERSION_CODES.O)
     fun getWeatherData(){
        coroutineScope.launch {

            var getDataDeffered= WeatherApi.retrofitService.getProperties(City,"metric",APPID)
            try{
                var listResult=getDataDeffered.await()
                _name.value= listResult.name?.capitalize()
                _temperature.value= listResult.main?.temp?.toInt().toString() +"°C"
                _description.value= listResult.weather.get(0).description.toString()
                _mintemp.value= "Min Temperature:" + listResult.main?.temp_min?.toInt().toString()
                _maxtemp.value= "Max Temperature:" + listResult.main?.temp_max?.toInt().toString()
                _sunrise.value= SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(listResult.sys?.sunrise?.times(1000)!!))
                _sunset.value=SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(listResult.sys?.sunset?.times(1000)!!))
                _wind.value= listResult.wind?.speed.toString()
                _pressure.value= listResult.main?.pressure.toString()
                _humidity.value=listResult.main?.humidity.toString()





            } catch (e:Exception){
               _response.value="fail: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}