package ru.vstu.clustermonitor.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.vstu.clustermonitor.MonitorApplication

abstract class BaseListViewModel: ViewModel()
{
    protected val _repository = MonitorApplication.Instance.monitorRepository
    protected val _isLoading = MutableLiveData<Boolean>()
    protected val _error = SingleLiveEvent<String>()

    val isLoading : LiveData<Boolean>
        get() = _isLoading

    val error : LiveData<String>
        get() = _error
}