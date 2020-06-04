package com.example.yoweather.weather

import com.example.yoweather.weather.adapter.GenericCollectionAdapterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import kotlinx.coroutines.Deferred
import retrofit2.http.Query


private val BASE_URL = "https://api.openweathermap.org/"

//val typed = Types.newParameterizedType(ArrayList::class.java, Weather::class.java)
//val adapter = moshi.adapter(typed)

private val moshi = Moshi.Builder()
    .add(GenericCollectionAdapterFactory(ArrayList::class.java) {ArrayList()})
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("data/2.5/weather")
    fun getProperties(@Query("q") City: String?, @Query("units") unit: String, @Query("appid") APP_ID: String):
            Deferred<WeatherData>

}



object WeatherApi {

    val retrofitService : WeatherApiService by lazy { retrofit.create(WeatherApiService::class.java) }

}