package com.damai.data.mapper

import com.damai.core.BaseMapper
import com.damai.data.model.HomeDataModel
import com.damai.data.model.SpecializationModel

/**
 * Created by damai.subimawanto on 7/10/2022.
 */
class HomeModelListToSpecializationModelListMapper : BaseMapper<List<HomeDataModel>?, List<SpecializationModel>>() {

    override fun map(value: List<HomeDataModel>?): List<SpecializationModel> {
        val dataList = arrayListOf<SpecializationModel>()
        value?.forEach { dataValue ->
            val index = dataList.indexOfFirst {
                it.id == dataValue.specializationId
            }
            if (index < 0) {
                dataList.add(
                    SpecializationModel(
                        id = dataValue.specializationId,
                        name = dataValue.specialization
                    )
                )
            }
        }
        return dataList
    }
}