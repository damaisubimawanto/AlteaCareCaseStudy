package com.damai.data.repository

import com.damai.core.NetworkResource
import com.damai.core.Resource
import com.damai.core.SchedulerProvider
import com.damai.data.mapper.HomeResponseToHomeModelListMapper
import com.damai.data.model.HomeModel
import com.damai.data.service.HomeService
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class HomeRepositoryImpl(
    private val homeService: HomeService,
    private val schedulerProvider: SchedulerProvider,
    private val homeResponseMapper: HomeResponseToHomeModelListMapper
) : HomeRepository {

    override fun getHome(): Flow<Resource<List<HomeModel>>> {
        return object : NetworkResource<List<HomeModel>>(
            schedulerProvider = schedulerProvider
        ) {
            override suspend fun remoteFetch(): List<HomeModel> {
                val response = homeService.getHome()
                return homeResponseMapper.map(response)
            }
        }.asFlow()
    }
}