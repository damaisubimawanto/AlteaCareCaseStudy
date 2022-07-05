package com.damai.data.mapper

import com.damai.core.BaseMapper
import com.damai.data.model.HomeModel
import com.damai.data.response.HomeResponse

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class HomeResponseToHomeModelListMapper : BaseMapper<HomeResponse, List<HomeModel>>() {

    override fun map(value: HomeResponse): List<HomeModel> {
        val dataList = arrayListOf<HomeModel>()
        value.data?.map { dataValue ->
            dataList.add(
                HomeModel(
                    id = dataValue.doctorId,
                    name = dataValue.name,
                    about = dataValue.about,
                    overview = dataValue.overview,
                    photoUrl = dataValue.photo?.url,
                    priceText = dataValue.price?.formatted,
                    specialization = dataValue.specialization?.name,
                    hospital = if (dataValue.hospital.isNullOrEmpty()) {
                        ""
                    } else {
                        dataValue.hospital!![0].name
                    }
                )
            )
        }
        return dataList
    }
}