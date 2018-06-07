package ru.vstu.clustermonitor.views.sensors

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import co.metalab.asyncawait.async
import ru.vstu.clustermonitor.models.Sensor
import ru.vstu.clustermonitor.utils.BaseListViewModel

class SensorsViewModel: BaseListViewModel {
    private val _sensors = MutableLiveData<List<Sensor>>()

    val sensors: LiveData<List<Sensor>>
        get() = _sensors

    constructor(){
        updateSensrs()
    }

    fun updateSensrs() {
        async {
            _isLoading.postValue(true)
            val data = await { _repository.getSensors() }
            _isLoading.postValue(false)

            if(data.isOk)
                _sensors.value = data.data
            else {
                _error.postValue("Не удалось данные с сенсоров. ${data?.error ?: "Неизвестная ошибка"}")
            }
        }
    }


}