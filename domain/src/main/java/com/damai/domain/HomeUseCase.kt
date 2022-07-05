package com.damai.domain

import com.damai.core.FlowUseCase
import com.damai.core.Resource
import com.damai.data.model.HomeModel
import com.damai.data.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class HomeUseCase(
    private val homeRepository: HomeRepository
): FlowUseCase<Nothing?, HomeModel>() {

    override suspend fun execute(parameters: Nothing?): Flow<Resource<HomeModel>> {
        return homeRepository.getHome()
    }
}