package ru.vstu.clustermonitor.Views.Fragments

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableInt
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_queue.*
import ru.vstu.clustermonitor.Models.QueueTask
import ru.vstu.clustermonitor.R
import ru.vstu.clustermonitor.databinding.FragmentQueueBinding

/**
 * This fragment displays current queue system status
 *
 * Displaying data:
 *  - List of current tasks in queue
 */
class QueueFragment : Fragment() {

    var counter: ObservableInt = ObservableInt(0)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding:  FragmentQueueBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_queue, container, false)
        binding.count = counter
        return binding.root

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_queue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnInc.setOnClickListener { view -> counter.set(counter.get() + 1) }
    }
}
