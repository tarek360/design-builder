package co.designbuilder.presentation.rx

import co.designbuilder.domain.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSchedulerProvider @Inject constructor() : SchedulerProvider {

    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }
}
