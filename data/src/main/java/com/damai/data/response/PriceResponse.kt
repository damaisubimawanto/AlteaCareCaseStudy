package com.damai.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class PriceResponse {

    @SerializedName("raw")
    var raw: Long = 0L

    @SerializedName("formatted")
    var formatted: String? = null
}