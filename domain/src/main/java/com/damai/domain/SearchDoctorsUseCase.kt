package com.damai.domain

import com.damai.core.FlowUseCase
import com.damai.core.Resource
import com.damai.data.model.HomeModel
import com.damai.data.model.SearchDoctorsModel
import com.damai.data.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by damai.subimawanto on 7/10/2022.
 */
class SearchDoctorsUseCase(
    private val homeRepository: HomeRepository
): FlowUseCase<SearchDoctorsModel?, HomeModel>() {

    override suspend fun execute(parameters: SearchDoctorsModel?): Flow<Resource<HomeModel>> {
        return homeRepository.getSearchedDoctors(
            doctorName = parameters?.doctorName,
            hospitalIds = parameters?.hospitalIds,
            specializationIds = parameters?.specializationIds
        ).flowOn(Dispatchers.IO)
    }
}