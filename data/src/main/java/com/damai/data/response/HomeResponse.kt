package com.damai.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class HomeResponse {

    @SerializedName("status")
    var status: Boolean = false

    @SerializedName("message")
    var message: String? = null

    @SerializedName("data")
    var data: List<HomeDataResponse>? = null
}