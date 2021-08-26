package com.example.covisocials

object LoginUtil {
    fun loginInput(
        userName : String,
        password : String
    ) : Boolean{
        if (userName.isBlank() || password.isBlank()){
            return false
        }
        if (password.length <= 6){
            return false
        }

        if (userName.contains("#")){
            return false
        }
        return true
    }
}