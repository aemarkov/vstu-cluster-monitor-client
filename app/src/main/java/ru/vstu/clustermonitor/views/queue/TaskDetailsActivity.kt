package ru.vstu.clustermonitor.views.queue

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.MenuItem
import ru.vstu.clustermonitor.R

class TaskDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        // Get id of task
        val bundle = intent.extras
        val jobId = bundle.getInt("job_id")

        val viewModel = ViewModelProviders.of(this)[TaskDetailsViewModel::class.java]
        viewModel.task.observe(this, Observer {
            if(it?.isOk==true && it.data != null)  // fuck kotlin
            {
                supportActionBar?.title = it.data.name
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
            NavUtils.navigateUpFromSameTask(this)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
