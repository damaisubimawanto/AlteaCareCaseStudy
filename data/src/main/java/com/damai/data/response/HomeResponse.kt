package com.damai.data.response

import com.damai.core.BaseResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class HomeResponse: BaseResponse() {

    @SerializedName("data")
    var data: List<HomeDataResponse>? = null
}