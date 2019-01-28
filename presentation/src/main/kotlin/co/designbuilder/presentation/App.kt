package co.designbuilder.presentation

import android.support.multidex.MultiDexApplication
import co.designbuilder.data.net.Backend
import co.designbuilder.presentation.di.*
import org.koin.android.ext.android.startKoin

open class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initComponent()
    }

    open fun initComponent() {
        Backend.instance.start(this)
        startKoin(this, allModules)
    }
}
