package ru.vstu.clustermonitor.views.queue

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import co.metalab.asyncawait.async
import ru.vstu.clustermonitor.MonitorApplication
import ru.vstu.clustermonitor.models.QueueTask
import ru.vstu.clustermonitor.utils.SingleLiveEvent

/**
 * View model for list of queue tasks
 */
class QueueViewModel : ViewModel {
    private val _repository = MonitorApplication.Instance.monitorRepository
    private val _tasks = MutableLiveData<List<QueueTask>>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _error = SingleLiveEvent<String>()
    private val _openTask = SingleLiveEvent<Int>()

    /**
     * List of current tasks in queue
     */
    val tasks : LiveData<List<QueueTask>>
        get() = _tasks

    val isLoading : LiveData<Boolean>
        get() = _isLoading

    val error : LiveData<String>
        get() = _error

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
                _error.postValue("Не удалось загрузить задачи: ${data?.error ?: "Неизвестная ошибка"}")
            }
        }
    }

    fun taskSelected(task: QueueTask){
        _openTask.postValue(task.job_id)
    }
}