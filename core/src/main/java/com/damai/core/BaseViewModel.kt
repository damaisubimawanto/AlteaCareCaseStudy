package com.damai.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/**
 * Created by damai.subimawanto on 7/4/2022.
 */
abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val parentJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = parentJob + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}