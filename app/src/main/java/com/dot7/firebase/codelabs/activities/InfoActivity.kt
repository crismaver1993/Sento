package com.dot7.firebase.codelabs.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dot7.firebase.R

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        getRas()
        
    }

    fun getRas(){
        Log.v("Raul Armas Santiago")

    }
}
