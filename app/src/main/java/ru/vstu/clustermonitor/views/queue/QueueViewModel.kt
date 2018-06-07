package ru.vstu.clustermonitor.views.queue

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import co.metalab.asyncawait.async
import ru.vstu.clustermonitor.models.QueueTask
import ru.vstu.clustermonitor.utils.BaseListViewModel
import ru.vstu.clustermonitor.utils.SingleLiveEvent

/**
 * View model for list of queue tasks
 */
class QueueViewModel : BaseListViewModel {
    private val _tasks = MutableLiveData<List<QueueTask>>()
    private val _openTask = SingleLiveEvent<Int>()

    /**
     * List of current tasks in queue
     */
    val tasks : LiveData<List<QueueTask>>
        get() = _tasks

    /**
     * Command to open task's details
     */
    val openTask : LiveData<Int>
        get() = _openTask

    constructor() {
        updateTasks()
    }

    /**
     * Request task list updating.
     * Result will be delivered through tasks LiveData
     */
    fun updateTasks(){
        async {

            _isLoading.postValue(true)
            val data = await { _repository.getQueueTasks() }
            _isLoading.postValue(false)

            if(data.isOk)
                _tasks.value = data.data
            else {
                _error.postValue("Не удалось загрузить задачи. ${data?.error ?: "Неизвестная ошибка"}")
            }
        }
    }

    fun taskSelected(task: QueueTask){
        _openTask.postValue(task.job_id)
    }
}