package com.damai.mycasestudy.module

import com.damai.domain.HomeUseCase
import com.damai.domain.SearchDoctorsUseCase
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
val useCaseModule = module {
    single {
        HomeUseCase(
            homeRepository = get()
        )
    }

    single {
        SearchDoctorsUseCase(
            homeRepository = get()
        )
    }
}