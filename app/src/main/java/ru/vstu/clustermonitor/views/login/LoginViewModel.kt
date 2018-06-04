package ru.vstu.clustermonitor.views.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import co.metalab.asyncawait.async
import ru.vstu.clustermonitor.MonitorApplication
import kotlin.math.log

class LoginViewModel : ViewModel() {

    private val _repository = MonitorApplication.Instance.monitorRepository
    private val _result = MutableLiveData<Boolean>()

    val login = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val result: LiveData<Boolean>
            get() = _result

    fun login() {
        async {
            val login  = login.value
            val pass = password.value
            if(login!=null && pass!=null) {
                val result = await { _repository.auth(login, pass) }
                _result.postValue(result)
            }
        }
    }

}