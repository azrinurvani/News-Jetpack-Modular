package com.example.newsinshort

import android.app.Application
import android.widget.Toast
import com.example.utilities.CoreUtility
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        checkInternetConnection()
    }

    private fun checkInternetConnection(){
        if (!CoreUtility.isInternetConnection(context = this)){
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }

    }
}