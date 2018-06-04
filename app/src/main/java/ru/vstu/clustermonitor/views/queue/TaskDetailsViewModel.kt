package ru.vstu.clustermonitor.views.queue

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import co.metalab.asyncawait.async
import ru.vstu.clustermonitor.MonitorApplication
import ru.vstu.clustermonitor.models.FailableModel
import ru.vstu.clustermonitor.models.QueueTask

class TaskDetailsViewModel : ViewModel() {
    private val _repository = MonitorApplication.Instance.monitorRepository
    private val _task = MutableLiveData<FailableModel<QueueTask>>()

    val task: LiveData<FailableModel<QueueTask>>
        get() = _task

    fun load(jobId: Int){
        async{
            val task = await { _repository.getQueueTask(jobId)}
            _task.postValue(task)
        }
    }
}