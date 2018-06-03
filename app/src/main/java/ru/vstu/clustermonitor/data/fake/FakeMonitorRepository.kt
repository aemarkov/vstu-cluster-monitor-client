package ru.vstu.clustermonitor.data.fake

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import co.metalab.asyncawait.async
import ru.vstu.clustermonitor.data.interfaces.IMonitorRepository
import ru.vstu.clustermonitor.models.FailableModel
import ru.vstu.clustermonitor.models.QueueTask
import java.util.*
import kotlin.concurrent.schedule

/**
 * Fake repository for testing
 */
class FakeMonitorRepository : IMonitorRepository {
    override fun getQueueTasks(): FailableModel<List<QueueTask>> {

        // Long request
        Thread.sleep(1000)

        if(Random().nextBoolean()) {
            // Request is success
            val list: MutableList<QueueTask> = mutableListOf()
            for (i in 1..3)
                list.add(QueueTask("Fake task $i"))

            return FailableModel(list)
        }
        else {
            // Request failed
            return FailableModel("Боги рандома не любят тебя")
        }
    }
}