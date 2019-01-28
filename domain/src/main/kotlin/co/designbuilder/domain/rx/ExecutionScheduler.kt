package co.designbuilder.domain.rx

import io.reactivex.Scheduler

open class ExecutionScheduler(val executionScheduler: Scheduler, val postExecutionScheduler: Scheduler)