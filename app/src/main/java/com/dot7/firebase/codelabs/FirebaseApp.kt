package com.dot7.firebase.codelabs

import android.app.Application
import androidx.multidex.MultiDex

class FirebaseApp : Application(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}