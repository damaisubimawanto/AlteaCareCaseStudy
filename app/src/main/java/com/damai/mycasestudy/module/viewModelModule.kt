package com.damai.mycasestudy.module

import com.damai.mycasestudy.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
val viewModelModule = module {
    viewModel {
        MainViewModel(
            homeUseCase = get(),
            searchDoctorsUseCase = get(),
            hospitalFilterCreationMapper = get(),
            specializationFilterCreationMapper = get(),
            hospitalTextListMapper = get(),
            specializationTextListMapper = get()
        )
    }
}