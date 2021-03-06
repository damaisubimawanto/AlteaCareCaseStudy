package com.damai.data.repository

import com.damai.core.Resource
import com.damai.data.model.HomeModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
interface HomeRepository {

    @Throws(Exception::class)
    fun getHome(): Flow<Resource<HomeModel>>

    @Throws(Exception::class)
    fun getSearchedDoctors(
        doctorName: String?,
        hospitalIds: List<String>?,
        specializationIds: List<String>?
    ): Flow<Resource<HomeModel>>
}