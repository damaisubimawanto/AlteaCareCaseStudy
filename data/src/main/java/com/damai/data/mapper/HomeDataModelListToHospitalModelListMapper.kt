package com.damai.data.mapper

import com.damai.core.BaseMapper
import com.damai.data.model.HomeDataModel
import com.damai.data.model.HospitalModel

/**
 * Created by damai.subimawanto on 7/10/2022.
 */
class HomeDataModelListToHospitalModelListMapper : BaseMapper<List<HomeDataModel>?, List<HospitalModel>>() {

    override fun map(value: List<HomeDataModel>?): List<HospitalModel> {
        val dataList = arrayListOf<HospitalModel>()
        value?.forEach { dataValue ->
            val index = dataList.indexOfFirst {
                it.id == dataValue.hospitalId
            }
            if (index < 0) {
                dataList.add(
                    HospitalModel(
                        id = dataValue.hospitalId,
                        name = dataValue.hospital
                    )
                )
            }
        }
        return dataList
    }
}