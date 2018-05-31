package ru.vstu.clustermonitor

import android.app.Application
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder

public class MonitorApplication : Application()
{
    companion object{
        public lateinit var Instance : MonitorApplication
    }

    private var cicerone: Cicerone<Router> =  Cicerone.create()

    public override fun onCreate()
    {
        super.onCreate()
        Instance = this
        super.onCreate()
    }

    public val router : Router
        get() = cicerone.router

    public  val navigatorHolder : NavigatorHolder
        get() = cicerone.navigatorHolder
}