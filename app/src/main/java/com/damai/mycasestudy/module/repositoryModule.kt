package com.damai.mycasestudy.module

import com.damai.data.repository.HomeRepository
import com.damai.data.repository.HomeRepositoryImpl
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
val repositoryModule = module {
    single<HomeRepository> {
        HomeRepositoryImpl(
            homeService = get(),
            schedulerProvider = get(),
            homeResponseMapper = get()
        )
    }
}