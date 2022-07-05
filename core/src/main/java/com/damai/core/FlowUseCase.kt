package com.damai.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
abstract class FlowUseCase<in P, R> {
    suspend operator fun invoke(parameters: P? = null): Flow<Resource<R>> = execute(parameters)
        .catch { e ->
            Resource.Error(
                ErrorData(
                    code = NetworkCodes.GENERIC_ERROR,
                    message = e.localizedMessage
                )
            )
        }

    protected abstract suspend fun execute(parameters: P? = null): Flow<Resource<R>>
}