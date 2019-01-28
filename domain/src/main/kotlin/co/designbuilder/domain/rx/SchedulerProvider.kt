package co.designbuilder.domain.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler
    fun main(): Scheduler
}