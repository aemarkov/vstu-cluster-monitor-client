package ru.vstu.clustermonitor.views

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import ru.vstu.clustermonitor.MonitorApplication
import ru.vstu.clustermonitor.NavigationTargets
import ru.vstu.clustermonitor.R
import ru.vstu.clustermonitor.views.camera.CameraFragment
import ru.vstu.clustermonitor.views.login.LoginActivity
import ru.vstu.clustermonitor.views.login.LoginFragment
import ru.vstu.clustermonitor.views.queue.QueueFragment
import ru.vstu.clustermonitor.views.resources.ResourcesFragment
import ru.vstu.clustermonitor.views.sensors.SensorsFragment


class MainActivity : AppCompatActivity(), LoginFragment.OnFragmentInteractionListener {

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

        Log.d("MainActivity", "onCreate")

        window.requestFeature(Window.FEATURE_ACTION_BAR)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // TODO: Is it correct?
        FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.topic_fuckups))
        FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.topic_move_sensor))

        /*// Check login status
        if(!MonitorApplication.Instance.monitorRepository.isLoggedIn())
        {
            //val intent = Intent(this, LoginActivity::class.java)
            //startActivity(intent)
            openLogin()
            return
        }

        openStartTab() */

        openLogin()
    }

    override fun onLogin(success: Boolean) {
        closeLogin()
    }

    private fun openLogin() {
        val fragment = LoginFragment()
        showFragment(fragment)
        supportActionBar?.hide()
        navigation.visibility = View.GONE
    }

    private fun closeLogin() {
        openStartTab()
        supportActionBar?.show()
        navigation.visibility = View.VISIBLE
    }

    private fun openStartTab() = navigateToTab(R.id.navigation_queue)

    private fun navigateToTab(id: Int) {

        val fragment : Fragment? = tabs[id]
        if(fragment == null) {
            Log.e(TAG, "Invalid ta id")
            return
        }

        showFragment(fragment, AnimSettings(R.anim.abc_fade_in, R.anim.abc_fade_out))
    }

    private fun showFragment(fragment: Fragment, anim: AnimSettings?=null)
    {
        val transation = supportFragmentManager.beginTransaction()

        if(anim!=null)
            transation.setCustomAnimations(anim.enter, anim.exit)

        transation.replace(R.id.fragment_container, fragment)
        transation.commit()
    }
}

data class AnimSettings(var enter: Int, var exit: Int)
