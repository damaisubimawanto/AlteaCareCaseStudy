package com.damai.data.mapper

import com.damai.core.BaseMapper
import com.damai.data.model.HospitalModel

/**
 * Created by damai.subimawanto on 7/10/2022.
 */
class HospitalModelListToStringListMapper : BaseMapper<List<HospitalModel>?, List<String>?>() {

    override fun map(value: List<HospitalModel>?): List<String>? {
        return value?.map {
            it.name ?: ""
        }
    }
}