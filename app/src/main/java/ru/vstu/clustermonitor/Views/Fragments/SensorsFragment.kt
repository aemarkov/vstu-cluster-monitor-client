package ru.vstu.clustermonitor.Views.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ru.vstu.clustermonitor.R

/**
 * This fragment displays data from sensors in cluster room
 *
 * Отображаемые данные:
 *  - Current temperature and humidity
 *  - Smoke and movement sensors
 *  - UPS voltage and operation time
 */
class SensorsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensors, container, false)
    }


}
