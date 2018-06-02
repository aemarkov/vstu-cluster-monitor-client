package ru.vstu.clustermonitor.views.queue

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
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

        //val binding  = DataBindingUtil.inflate<FragmentQueueBinding>(inflater, R.layout.fragment_queue, container, false)
        //binding.viewModel  = viewModel
        //return binding.root

        return inflater.inflate(R.layout.fragment_queue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)[QueueViewModel::class.java]
        queue_task_list.layoutManager = LinearLayoutManager(activity)

        // Begin loading animation
        queue_task_refresh.isRefreshing = true

        // Display tasks when they will be ready
        viewModel.queueTasks.observe(this, Observer {
            // Stop loading animation
            queue_task_refresh.isRefreshing = false

            if(it == null || !it.isOk)
                Snackbar.make(view, "Не удалось загрузить задачи: ${it?.error ?: "Неизвестная ошибка"}", Snackbar.LENGTH_SHORT).show()
            else
                queue_task_list.adapter = QueueTasksAdapter(it.data!!) // data couldn't be null, because it.isOk == true
        })

        // Refresh event
        queue_task_refresh.setOnRefreshListener {
            viewModel.updateTasks()
        }
    }
}
