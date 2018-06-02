package ru.vstu.clustermonitor.views.queue

import android.app.Application
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import ru.vstu.clustermonitor.MonitorApplication
import ru.vstu.clustermonitor.models.QueueTask

/**
 * View model for list of queue tasks
 */
class QueueViewModel : ViewModel() {
    private val repository = MonitorApplication.Instance.monitorRepository
    val queueTasks = repository.GetQueueTasks()

    fun updateTasks()
    {

    }
}