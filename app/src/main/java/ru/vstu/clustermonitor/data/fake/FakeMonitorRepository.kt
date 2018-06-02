package ru.vstu.clustermonitor.data.fake

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.vstu.clustermonitor.data.interfaces.IMonitorRepository
import ru.vstu.clustermonitor.models.FailableModel
import ru.vstu.clustermonitor.models.QueueTask
import java.util.*
import kotlin.concurrent.schedule

/**
 * Fake repository for testing
 */
class FakeMonitorRepository : IMonitorRepository {
    override fun GetQueueTasks(): LiveData<FailableModel<List<QueueTask>>> {
        val data = MutableLiveData<FailableModel<List<QueueTask>>>()

        // Emulate callback after long network request is completed
        Timer().schedule(3000) {

            if(Random().nextBoolean()) {
                // Request is success
                val list: MutableList<QueueTask> = mutableListOf()
                for (i in 1..5)
                    list.add(QueueTask("Fake task $i"))

                data.postValue(FailableModel(list))
            }
            else {
                // Request failed
                data.postValue(FailableModel("Боги рандома не любят тебя"))
            }

        }

        return data
    }
}
