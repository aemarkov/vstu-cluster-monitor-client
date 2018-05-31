package ru.vstu.clustermonitor.firebase

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.vstu.clustermonitor.Views.Activities.MainActivity
import ru.vstu.clustermonitor.R

class MessagingService : FirebaseMessagingService()
{
    val TAG = "MessagingService"
    val NOTIFICATION_ID = 1

    /**
     * @brief Called if message is received
     *
     *                     Foreground          Background
     * Notification msg     called              -
     * Data msg             called              called
     * Data + Notification  called              -
     */
    public override fun onMessageReceived(message: RemoteMessage?) {

        if(message == null) {
            Log.w(TAG, "Received message is null")
            return
        }

        Log.d(TAG, "Received message from ${message.from}")
        for((key, value) in message.data)
        {
            Log.d(TAG, "$key: $value")
        }

        createNotification(message.data)
    }

    private fun createNotification(data: Map<String, String>)
    {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        //TODO: Add channels for Android 8
        var soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        var notification = NotificationCompat.Builder(this)
                .setContentText(data["body"])
                .setContentTitle(data["title"])
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setSound(soundUri)
                .build()

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }
}