package com.damai.mycasestudy.presentation

import androidx.lifecycle.MutableLiveData
import com.damai.core.BaseViewModel
import com.damai.core.Resource
import com.damai.data.model.HomeDataModel
import com.damai.domain.HomeUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class MainViewModel(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    var keyword: String? = null

    val loading = MutableLiveData(false)
    val doctorListResponse = MutableLiveData<List<HomeDataModel>>()
    val searchedListResponse = MutableLiveData<List<HomeDataModel>>()

    init {
        getHome()
    }

    fun getHome() {
        launch {
            homeUseCase().onStart {
                loading.value = true
            }.collect { resource ->
                loading.value = false
                when (resource) {
                    is Resource.Success -> {
                        doctorListResponse.value = resource.model?.data
                    }
                    is Resource.Error -> {

                    }
                    else -> {}
                }
            }
        }
    }
}