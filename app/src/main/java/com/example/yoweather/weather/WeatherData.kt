package com.example.yoweather.weather

import com.squareup.moshi.Json

class WeatherData {


    @Json(name = "coord")

    var coord: Coord? = null

    @Json(name = "sys")

    var sys: Sys? = null

    @Json(name = "weather")

    var weather = ArrayList<Weather>()

    @Json(name = "main")

    var main: Main? = null

    @Json(name = "wind")

    var wind: Wind? = null

    @Json(name = "rain")

    var rain: Rain? = null

    @Json(name = "clouds")

    var clouds: Clouds? = null

    @Json(name = "dt")

    var dt: Float = 0.toFloat()

    @Json(name = "id")

    var id: Int = 0

    @Json(name = "name")

    var name: String? = null

    @Json(name = "cod")

    var cod: Float = 0.toFloat()

}


class Weather {

    @Json(name = "id")

    var id: Int = 0

    @Json(name = "main")

    var main: String? = null

    @Json(name = "description")

    var description: String? = null

    @Json(name = "ic" + "on")

    var icon: String? = null

}


class Clouds {

    @Json(name = "all")

    var all: Float = 0.toFloat()

}


class Rain {

    @Json(name = "3h")

    var h3: Float = 0.toFloat()

}


class Wind {

    @Json(name = "speed")

    var speed: Float = 0.toFloat()

    @Json(name = "deg")

    var deg: Float = 0.toFloat()

}


class Main {

    @Json(name = "temp")

    var temp: Float = 0.toFloat()

    @Json(name = "humidity")

    var humidity: Float = 0.toFloat()

    @Json(name = "pressure")

    var pressure: Float = 0.toFloat()

    @Json(name = "temp_min")

    var temp_min: Float = 0.toFloat()

    @Json(name = "temp_max")

    var temp_max: Float = 0.toFloat()

}


class Sys {

    @Json(name = "country")

    var country: String? = null

    @Json(name = "sunrise")

    var sunrise: Long = 0

    @Json(name = "sunset")

    var sunset: Long = 0

}


class Coord {

    @Json(name = "lon")

    var lon: Float = 0.toFloat()

    @Json(name = "lat")

    var lat: Float = 0.toFloat()

}