package com.damai.core

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
interface SchedulerProvider {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
}