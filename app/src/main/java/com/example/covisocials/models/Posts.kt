package com.example.covisocials.models

//data class for post contents
data class Posts(
    var caption : String = "",
    var current_time : Long = 0,
    var image_url : String = "",
    var user: Users?= null //this one points to the "users" node/data class in firestore
)