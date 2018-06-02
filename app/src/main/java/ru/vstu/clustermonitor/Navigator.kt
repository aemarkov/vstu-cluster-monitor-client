package ru.vstu.clustermonitor

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command
import ru.vstu.clustermonitor.views.camera.CameraFragment
import ru.vstu.clustermonitor.views.queue.QueueFragment
import ru.vstu.clustermonitor.views.resources.ResourcesFragment
import ru.vstu.clustermonitor.views.sensors.SensorsFragment

public class Navigator(fragmentManager: FragmentManager?, containerId: Int) : SupportFragmentNavigator(fragmentManager, containerId) {

    override fun createFragment(screenKey: String?, data: Any?): Fragment {
        return when(screenKey) {
            NavigationTargets.QUEUE -> QueueFragment()
            NavigationTargets.RESOURCES -> ResourcesFragment()
            NavigationTargets.SENSORS -> SensorsFragment()
            NavigationTargets.CAMERA -> CameraFragment()
            else -> throw Exception("Invalid screenKey")
        }
    }

    override fun exit() {
        TODO("not implemented")
    }

    override fun showSystemMessage(message: String?) {
        TODO("not implemented")
    }

    override fun setupFragmentTransactionAnimation(command: Command?,
                                                   currentFragment: Fragment?,
                                                   nextFragment: Fragment?,
                                                   fragmentTransaction: FragmentTransaction?) {
        /*super.setupFragmentTransactionAnimation(
                command,
                currentFragment,
                nextFragment,
                fragmentTransaction
        )*/

        fragmentTransaction?.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }
}

class NavigationTargets {
    companion object {
        val QUEUE = "QUEUE"
        val RESOURCES = "RESOURCES"
        val SENSORS = "SENSORS"
        val CAMERA = "CAMERA"
    }
}