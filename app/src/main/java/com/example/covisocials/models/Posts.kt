package com.example.covisocials.models

data class Posts(
    var caption : String = "",
    var current_time : Long = 0,
    var image_url : String = "",
    var user: Users?= null
)