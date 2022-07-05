package com.damai.mycasestudy.module

import com.damai.data.mapper.HomeResponseToHomeModelMapper
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
val mapperModule = module {
    factory { HomeResponseToHomeModelMapper() }
}