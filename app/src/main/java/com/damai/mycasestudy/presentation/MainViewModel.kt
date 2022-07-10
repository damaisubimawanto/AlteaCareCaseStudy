package com.damai.mycasestudy.presentation

import androidx.lifecycle.MutableLiveData
import com.damai.core.BaseViewModel
import com.damai.core.Resource
import com.damai.data.mapper.HomeDataModelListToHospitalModelListMapper
import com.damai.data.mapper.HomeModelListToSpecializationModelListMapper
import com.damai.data.mapper.HospitalModelListToStringListMapper
import com.damai.data.mapper.SpecializationModelListToStringListMapper
import com.damai.data.model.HomeDataModel
import com.damai.data.model.HospitalModel
import com.damai.data.model.SpecializationModel
import com.damai.domain.HomeUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class MainViewModel(
    private val homeUseCase: HomeUseCase,
    private val hospitalFilterCreationMapper: HomeDataModelListToHospitalModelListMapper,
    private val specializationFilterCreationMapper: HomeModelListToSpecializationModelListMapper,
    private val hospitalTextListMapper: HospitalModelListToStringListMapper,
    private val specializationTextListMapper: SpecializationModelListToStringListMapper
) : BaseViewModel() {

    var keyword: String? = null
    var hospitalFilterList: List<HospitalModel>? = null
    var specializationFilterList: List<SpecializationModel>? = null
    var lastHospitalSelections = IntArray(0)
    var lastSpecializationSelections = IntArray(0)

    val loading = MutableLiveData(false)
    val doctorListResponse = MutableLiveData<List<HomeDataModel>>()
    val searchedListResponse = MutableLiveData<List<HomeDataModel>>()

    init {
        getHome()
    }

    private fun getHome() {
        launch {
            homeUseCase().onStart {
                loading.value = true
            }.collect { resource ->
                loading.value = false
                when (resource) {
                    is Resource.Success -> {
                        doctorListResponse.value = resource.model?.data
                        hospitalFilterList = hospitalFilterCreationMapper.map(
                            resource.model?.data
                        )
                        specializationFilterList = specializationFilterCreationMapper.map(
                            resource.model?.data
                        )
                    }
                    is Resource.Error -> {

                    }
                    else -> {}
                }
            }
        }
    }

    fun generateHospitalTextList(): List<String>? {
        return hospitalTextListMapper.map(hospitalFilterList)
    }

    fun generateSpecializationTextList(): List<String>? {
        return specializationTextListMapper.map(specializationFilterList)
    }
}