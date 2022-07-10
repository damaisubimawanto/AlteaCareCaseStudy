package com.damai.data.mapper

import com.damai.core.BaseMapper
import com.damai.data.model.HomeDataModel
import com.damai.data.model.HomeModel
import com.damai.data.response.HomeDataResponse
import com.damai.data.response.HomeResponse

/**
 * Created by damai.subimawanto on 7/10/2022.
 */
class HomeResponseToSearchedHomeModelMapper : BaseMapper<HomeResponse, HomeModel>() {

    private var mDoctorName: String? = null
    private var mHospitalIds: List<String>? = null
    private var mSpecializationIds: List<String>? = null

    override fun map(value: HomeResponse): HomeModel {
        val dataList = arrayListOf<HomeDataModel>()
        if (mDoctorName.isNullOrBlank()) {
            value.data?.forEach {
                dataList.add(
                    convertFromResponseToModel(dataResponse = it)
                )
            }
        } else {
            filterByName(givenResponseValue = value.data)?.let {
                dataList.addAll(it)
            }
        }
        if (!mHospitalIds.isNullOrEmpty()) {
            filterByHospital(givenModelValue = dataList)
        }
        if (!mSpecializationIds.isNullOrEmpty()) {
            filterBySpecialization(givenModelValue = dataList)
        }

        return HomeModel(
            data = dataList
        ).apply {
            status = value.status
            message = value.message
        }
    }

    private fun convertFromResponseToModel(
        dataResponse: HomeDataResponse
    ): HomeDataModel {
        return HomeDataModel(
            id = dataResponse.doctorId,
            name = dataResponse.name,
            about = dataResponse.about,
            overview = dataResponse.overview,
            photoUrl = dataResponse.photo?.url,
            priceText = dataResponse.price?.formatted,
            specializationId = dataResponse.specialization?.id,
            specialization = dataResponse.specialization?.name,
            hospitalId = if (dataResponse.hospital.isNullOrEmpty()) {
                ""
            } else {
                dataResponse.hospital!![0].id
            },
            hospital = if (dataResponse.hospital.isNullOrEmpty()) {
                ""
            } else {
                dataResponse.hospital!![0].name
            }
        )
    }

    private fun filterByName(
        givenResponseValue: List<HomeDataResponse>?
    ): List<HomeDataModel>? {
        var resultData: MutableList<HomeDataModel>? = null
        givenResponseValue?.forEach { dataValue ->
            if (dataValue.name?.contains(other = mDoctorName!!, ignoreCase = true) == true) {
                if (resultData == null) {
                    resultData = arrayListOf()
                }
                resultData!!.add(
                    convertFromResponseToModel(dataResponse = dataValue)
                )
            }
        }
        return resultData?.toList()
    }

    private fun filterByHospital(
        givenModelValue: ArrayList<HomeDataModel>
    ) {
        val resultData = arrayListOf<HomeDataModel>()
        givenModelValue.forEach { dataValue ->
            mHospitalIds!!.forEach { hospitalId ->
                if (dataValue.hospitalId == hospitalId) {
                    resultData.add(dataValue)
                }
            }
        }
        givenModelValue.clear()
        givenModelValue.addAll(resultData)
    }

    private fun filterBySpecialization(
        givenModelValue: ArrayList<HomeDataModel>
    ) {
        val resultData = arrayListOf<HomeDataModel>()
        givenModelValue.forEach { dataValue ->
            mSpecializationIds!!.forEach { specializationId ->
                if (dataValue.specializationId == specializationId) {
                    resultData.add(dataValue)
                }
            }
        }
        givenModelValue.clear()
        givenModelValue.addAll(resultData)
    }

    fun customMap(
        doctorName: String?,
        hospitalIds: List<String>?,
        specializationIds: List<String>?,
        value: HomeResponse
    ): HomeModel {
        mDoctorName = doctorName
        mHospitalIds = hospitalIds
        mSpecializationIds = specializationIds
        return map(value = value)
    }
}