package ru.vstu.clustermonitor.views

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import ru.vstu.clustermonitor.MonitorApplication
import ru.vstu.clustermonitor.NavigationTargets
import ru.vstu.clustermonitor.Navigator
import ru.vstu.clustermonitor.R


class MainActivity : AppCompatActivity() {

    val navigator : Navigator  = Navigator(supportFragmentManager, R.id.fragment_container)

    override fun onResume() {
        super.onResume()
        MonitorApplication.Instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        MonitorApplication.Instance.navigatorHolder.removeNavigator()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_queue -> {
                //navController.navigate(R.id.queueFragment)
                MonitorApplication.Instance.router.newRootScreen(NavigationTargets.QUEUE)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_resources -> {
                //navController.navigate(R.id.resourcesFragment)
                MonitorApplication.Instance.router.newRootScreen(NavigationTargets.RESOURCES)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_sensors -> {
                //navController.navigate(R.id.sensorsFragment)
                MonitorApplication.Instance.router.newRootScreen(NavigationTargets.SENSORS)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_camera -> {
                //navController.navigate(R.id.cameraFragment)
                MonitorApplication.Instance.router.newRootScreen(NavigationTargets.CAMERA)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        // TODO: Is it correct?
        FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.topic_fuckups))
        FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.topic_move_sensor))

        MonitorApplication.Instance.router.newRootScreen(NavigationTargets.QUEUE)
    }
}
