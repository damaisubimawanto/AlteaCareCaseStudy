package com.damai.data.repository

import com.damai.core.NetworkResource
import com.damai.core.Resource
import com.damai.core.SchedulerProvider
import com.damai.data.mapper.HomeResponseToHomeModelMapper
import com.damai.data.model.HomeModel
import com.damai.data.service.HomeService
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class HomeRepositoryImpl(
    private val homeService: HomeService,
    private val schedulerProvider: SchedulerProvider,
    private val homeResponseMapper: HomeResponseToHomeModelMapper
) : HomeRepository {

    override fun getHome(): Flow<Resource<HomeModel>> {
        return object : NetworkResource<HomeModel>(
            schedulerProvider = schedulerProvider
        ) {
            override suspend fun remoteFetch(): HomeModel {
                val response = homeService.getHome()
                return homeResponseMapper.map(response)
            }
        }.asFlow()
    }
}