package com.dot7.firebase.codelabs

import android.app.Application
import android.util.Log
import androidx.multidex.MultiDex

class FirebaseApp : Application(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Log.v("xxx","-")

    }
}