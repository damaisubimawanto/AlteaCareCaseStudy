package com.damai.data.mapper

import com.damai.core.BaseMapper
import com.damai.data.model.SpecializationModel

/**
 * Created by damai.subimawanto on 7/10/2022.
 */
class SpecializationModelListToStringListMapper : BaseMapper<List<SpecializationModel>?, List<String>?>() {

    override fun map(value: List<SpecializationModel>?): List<String>? {
        return value?.map {
            it.name ?: ""
        }
    }
}