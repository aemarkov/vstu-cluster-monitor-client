package ru.vstu.clustermonitor.views.queue

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_queue.*
import ru.vstu.clustermonitor.R
import ru.vstu.clustermonitor.databinding.NodeItemBinding
import ru.vstu.clustermonitor.databinding.QueueTaskItemBinding
import ru.vstu.clustermonitor.models.QueueTask
import ru.vstu.clustermonitor.utils.BindingAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!)[QueueViewModel::class.java]

        // List of tasks updates
        viewModel.tasks.observe(this, Observer
        {
            queue_task_list.layoutManager = LinearLayoutManager(activity)
            queue_task_list.adapter = object: BindingAdapter<QueueTask, QueueTaskItemBinding>(
                    it!!,
                    R.layout.queue_task_item,
                    {viewModel.taskSelected(it)})
            {
                override fun setBinding(binding: QueueTaskItemBinding, item: QueueTask) { binding.task = item }
            }

        })

        // Error notification
        viewModel.error.observe(this, Observer {
            Snackbar.make(view, it!!, Snackbar.LENGTH_SHORT).show()
        })

        // Set loading indicator
        viewModel.isLoading.observe(this, Observer {
            queue_task_refresh.isRefreshing = it == true
        })

        viewModel.openTask.observe(this, Observer {
            if(it!=null)
            {
                val intent = Intent(activity, TaskDetailsActivity::class.java)
                val b = Bundle()
                b.putInt("job_id", it)
                intent.putExtras(b)
                startActivity(intent)
            }
        })

        // Refresh event
        queue_task_refresh.setOnRefreshListener {
            viewModel.updateTasks()
        }
    }
}
