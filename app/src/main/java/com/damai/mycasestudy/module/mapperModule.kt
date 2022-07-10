package com.damai.mycasestudy.module

import com.damai.data.mapper.*
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
val mapperModule = module {
    factory { HomeResponseToHomeModelMapper() }
    factory { HomeResponseToSearchedHomeModelMapper() }
    factory { HomeDataModelListToHospitalModelListMapper() }
    factory { HomeModelListToSpecializationModelListMapper() }
    factory { HospitalModelListToStringListMapper() }
    factory { SpecializationModelListToStringListMapper() }
}