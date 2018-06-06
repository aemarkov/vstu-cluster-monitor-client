package ru.vstu.clustermonitor.views.queue

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.MenuItem
import ru.vstu.clustermonitor.R
import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_task_details.*
import kotlinx.android.synthetic.main.fragment_queue.*
import ru.vstu.clustermonitor.databinding.ActivityTaskDetailsBinding
import ru.vstu.clustermonitor.databinding.NodeItemBinding
import ru.vstu.clustermonitor.databinding.QueueTaskItemBinding
import ru.vstu.clustermonitor.models.QueueTask
import ru.vstu.clustermonitor.utils.BindingAdapter


class TaskDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        // Get id of task
        val bundle = intent.extras
        val jobId = bundle.getInt("job_id")

        val viewModel = ViewModelProviders.of(this)[TaskDetailsViewModel::class.java]
        val binding = DataBindingUtil.setContentView<ActivityTaskDetailsBinding>(this, R.layout.activity_task_details)

        viewModel.task.observe(this, Observer {
            if(it?.isOk==true && it.data != null)  // fuck kotlin
            {
                supportActionBar?.title = it.data.name
                binding.task = it.data

                nodes_list.layoutManager = LinearLayoutManager(this)
                nodes_list.adapter = object: BindingAdapter<String, NodeItemBinding>(
                        listOf("1", "2", "3", "4", "5", "6"),//it.data.nodes,
                        R.layout.node_item,
                        {})
                {
                    override fun setBinding(binding: NodeItemBinding, item: String) {
                        binding.node = item
                    }
                }
            }
            else
            {
                // Close activity
            }
        })

        viewModel.load(jobId)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        if(item?.itemId == android.R.id.home)
        {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
