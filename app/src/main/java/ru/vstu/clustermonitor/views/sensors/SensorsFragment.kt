package ru.vstu.clustermonitor.views.sensors


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_sensors.*
import ru.vstu.clustermonitor.R
import ru.vstu.clustermonitor.databinding.SensorItemBinding
import ru.vstu.clustermonitor.models.Sensor
import ru.vstu.clustermonitor.utils.BindingAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(activity!!)[SensorsViewModel::class.java]

        // Set items
        viewModel.sensors.observe(this, Observer {
            sensors_list.layoutManager = LinearLayoutManager(activity)
            sensors_list.adapter = object: BindingAdapter<Sensor, SensorItemBinding>(
                    it!!,
                    R.layout.sensor_item,
                    {})
            {
                override fun setBinding(binding: SensorItemBinding, item: Sensor) { binding.sensor = item }
            }
        })

        // Error notification
        viewModel.error.observe(this, Observer {
            Snackbar.make(view, it!!, Snackbar.LENGTH_SHORT).show()
        })

        // Set loading indicator
        viewModel.isLoading.observe(this, Observer {
            sensors_list_refresh.isRefreshing = it == true
        })

        // Refresh event
        sensors_list_refresh.setOnRefreshListener {
            viewModel.updateSensrs()
        }
    }


}
