package ru.vstu.clustermonitor.views.queue

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_queue.*
import ru.vstu.clustermonitor.R
import ru.vstu.clustermonitor.models.FailableModel

/**
 * This fragment displays current queue system status
 *
 * Displaying data:
 *  - List of current tasks in queue
 */
class QueueFragment : Fragment() {

    lateinit var viewModel : QueueViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_queue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _activity = activity
        if(_activity==null)
        {
            Log.e("QueueFragment", "Activity is null. Да как так-то?!")
            return
        }

        viewModel = ViewModelProviders.of(_activity)[QueueViewModel::class.java]
        queue_task_list.layoutManager = LinearLayoutManager(activity)

        viewModel.tasks.observe(this, Observer {
            queue_task_list.adapter = QueueTasksAdapter(it!!)
        })

        viewModel.error.observe(this, Observer {
            Snackbar.make(view, it!!, Snackbar.LENGTH_SHORT).show()
        })

        viewModel.isLoading.observe(this, Observer {
            queue_task_refresh.isRefreshing = it!!
        })

        // Refresh event
        queue_task_refresh.setOnRefreshListener {
            viewModel.updateTasks()
        }
    }
}
