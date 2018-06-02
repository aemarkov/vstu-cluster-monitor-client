package ru.vstu.clustermonitor.data.interfaces

import android.arch.lifecycle.LiveData
import ru.vstu.clustermonitor.models.FailableModel
import ru.vstu.clustermonitor.models.QueueTask

/**
 * Interface of cluster monitoring information repository
 * Incapsulates all work with backend
 */
interface IMonitorRepository {

    /**
     * Fetch list of current tasks
     */
    fun GetQueueTasks():  LiveData<FailableModel<List<QueueTask>>>
}
