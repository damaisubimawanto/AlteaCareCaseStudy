package com.damai.data.model

import com.damai.core.BaseModel

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
data class HomeModel(
    val data: List<HomeDataModel>?
) : BaseModel()