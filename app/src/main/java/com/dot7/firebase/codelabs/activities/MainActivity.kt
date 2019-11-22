package com.dot7.firebase.codelabs.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dot7.firebase.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRemoteConfig()

        Log.v("xxx","-")
    }

    private fun setUpRemoteConfig() {
        //Inicialización del Remote Config
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        //COnfiguración
        remoteConfig.setDefaultsAsync(R.xml.remote_config)

        val settings = FirebaseRemoteConfigSettings.Builder()
        settings.setFetchTimeoutInSeconds(3600)
        remoteConfig.setConfigSettingsAsync(settings.build())

        //Lectura de infomación
        remoteConfig.fetchAndActivate().addOnCompleteListener(this){request ->
            if(request.isSuccessful){
             val message = remoteConfig.getString("message")
                message?.let{tv_data.text = it}
            }else{

                Toast.makeText(this,"No pudo sincronizar", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun getToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.v("xxxMainActivity", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                val msg = "Tu token es: $token"
                Log.d("xxxMainActivity", msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })
    }
}
