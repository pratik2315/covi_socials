package com.example.covisocials

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ms app centre dependancy code
        AppCenter.start(
            application, "b07a9072-b9bb-4718-bc2f-a3bdcf93f7a8",
            Analytics::class.java, Crashes::class.java
        )
    }
}