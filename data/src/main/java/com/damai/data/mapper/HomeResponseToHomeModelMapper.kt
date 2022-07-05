package com.damai.data.mapper

import com.damai.core.BaseMapper
import com.damai.data.model.HomeDataModel
import com.damai.data.model.HomeModel
import com.damai.data.response.HomeResponse

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class HomeResponseToHomeModelMapper : BaseMapper<HomeResponse, HomeModel>() {

    override fun map(value: HomeResponse): HomeModel {
        val dataList = arrayListOf<HomeDataModel>()
        value.data?.map { dataValue ->
            dataList.add(
                HomeDataModel(
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

        return HomeModel(
            data = dataList
        ).apply {
            status = value.status
            message = value.message
        }
    }
}