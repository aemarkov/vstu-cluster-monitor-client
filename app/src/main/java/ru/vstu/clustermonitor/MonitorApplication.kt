package ru.vstu.clustermonitor

import android.app.Application
import android.content.Context
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.vstu.clustermonitor.data.interfaces.IMonitorRepository
import ru.vstu.clustermonitor.data.retrofit.RetrofitMonitorRepository

class MonitorApplication : Application()
{
    companion object{
        lateinit var Instance : MonitorApplication
    }


    lateinit var monitorRepository: IMonitorRepository
        private set

    override fun onCreate()
    {
        super.onCreate()
        Instance = this
        monitorRepository = RetrofitMonitorRepository()
    }
}