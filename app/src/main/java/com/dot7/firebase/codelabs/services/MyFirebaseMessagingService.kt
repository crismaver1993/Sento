package com.dot7.firebase.codelabs.services

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.dot7.firebase.R
import com.dot7.firebase.codelabs.activities.InfoActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.let {
            Log.v(TAG, "xxxFCM Message Id: " + it.messageId.toString())
            Log.v(TAG, "xxxFCM Notification Message: " + it.notification?.body)
            Log.v(TAG, "xxxFCM Data Message: " + it.data)
        }

        sendNotification()
    }

    private fun sendNotification() {
        val intent = Intent(this, InfoActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val clickIntent = PendingIntent.getActivity(this, 12, intent, PendingIntent.FLAG_ONE_SHOT)

        val channelId = "sento_canal_id"
        val sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this@MyFirebaseMessagingService)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Title ")
            .setContentText("Mi mensaje")
            .setAutoCancel(true)
            .setSound(sonido)
            .setContentIntent(clickIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(1, notificationBuilder.build())
    }


    companion object {
        private val TAG = "MyFMService"
    }
}
