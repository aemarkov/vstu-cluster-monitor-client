package ru.vstu.clustermonitor.views

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import ru.vstu.clustermonitor.MonitorApplication
import ru.vstu.clustermonitor.NavigationTargets
import ru.vstu.clustermonitor.R
import ru.vstu.clustermonitor.views.camera.CameraFragment
import ru.vstu.clustermonitor.views.login.LoginActivity
import ru.vstu.clustermonitor.views.queue.QueueFragment
import ru.vstu.clustermonitor.views.resources.ResourcesFragment
import ru.vstu.clustermonitor.views.sensors.SensorsFragment


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val tabs = hashMapOf(
            R.id.navigation_queue to QueueFragment(),
            R.id.navigation_resources to ResourcesFragment(),
            R.id.navigation_sensors to SensorsFragment(),
            R.id.navigation_camera to CameraFragment()
    )

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        navigateToTab(item.itemId)
        true //???
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // TODO: Is it correct?
        FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.topic_fuckups))
        FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.topic_move_sensor))

        // Check login status
        if(!MonitorApplication.Instance.monitorRepository.isLoggedIn())
        {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            return
        }

        navigateToTab(R.id.navigation_queue)
    }

    private fun navigateToTab(id: Int) {
        val transation = supportFragmentManager.beginTransaction()

        val fragment : Fragment? = tabs[id]
        if(fragment == null) {
            Log.e(TAG, "Invalid ta id")
            return
        }

        transation.replace(R.id.fragment_container, fragment)
        transation.commit()
    }
}
