package ru.vstu.clustermonitor.Views.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.vstu.clustermonitor.R

/**
 * This fragment displays current queue system status
 *
 * Displaying data:
 *  - List of current tasks in queue
 */
class QueueFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_queue, container, false)
    }
}
