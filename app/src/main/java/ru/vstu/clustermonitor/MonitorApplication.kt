package ru.vstu.clustermonitor

import android.app.Application
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.vstu.clustermonitor.data.fake.FakeMonitorRepository
import ru.vstu.clustermonitor.data.interfaces.IMonitorRepository

class MonitorApplication : Application()
{
    companion object{
        lateinit var Instance : MonitorApplication
    }

    private var cicerone: Cicerone<Router> =  Cicerone.create()

    val router : Router
        get() = cicerone.router

    val navigatorHolder : NavigatorHolder
        get() = cicerone.navigatorHolder

    lateinit var monitorRepository: IMonitorRepository
        private set

    override fun onCreate()
    {
        super.onCreate()
        Instance = this
        monitorRepository = FakeMonitorRepository()
    }
}