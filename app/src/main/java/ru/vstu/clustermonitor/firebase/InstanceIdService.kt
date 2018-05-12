package ru.vstu.clustermonitor.firebase

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class InstanceIdService : FirebaseInstanceIdService()
{
    /**
     * Called if InstanceId token created or updated
     */
    public override fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
        Log.d("InstanceIdService", "New token: $token")

        // TODO: If it necessary do something with token
    }
}