package com.damai.mycasestudy.presentation

import com.damai.core.BaseViewModel
import com.damai.core.Resource
import com.damai.domain.HomeUseCase
import kotlinx.coroutines.launch

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class MainViewModel(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    fun getHome() {
        launch {
            homeUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> {

                    }
                    is Resource.Error -> {

                    }
                    else -> {}
                }
            }
        }
    }
}