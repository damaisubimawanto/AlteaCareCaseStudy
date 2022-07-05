package com.damai.core

import kotlinx.coroutines.Dispatchers

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class ApplicationDispatchersProvider : SchedulerProvider {
    override fun io() = Dispatchers.IO
    override fun ui() = Dispatchers.Main
    override fun default() = Dispatchers.Default
}