package ru.vstu.clustermonitor.data.interfaces

import android.arch.lifecycle.LiveData
import ru.vstu.clustermonitor.models.FailableModel
import ru.vstu.clustermonitor.models.QueueTask
import ru.vstu.clustermonitor.models.Sensor

/**
 * Interface of cluster monitoring information repository
 * Incapsulates all work with backend
 */
interface IMonitorRepository {

    /**
     * Is user already logged in
     */
    fun isLoggedIn(): Boolean

    /**
     * Authentication
     */
    fun auth(login: String, password: String): Boolean

    /**
     * Fetch list of current tasks
     */
    fun getQueueTasks():  FailableModel<List<QueueTask>>

    /**
     * Get task with given Id
     */
    fun getQueueTask(id: Int): FailableModel<QueueTask>

    /**
     * Fetch list of sensors
     */
    fun getSensors(): FailableModel<List<Sensor>>
}
