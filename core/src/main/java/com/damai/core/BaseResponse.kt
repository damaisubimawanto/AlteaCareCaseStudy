package com.damai.core

import com.google.gson.annotations.SerializedName

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
open class BaseResponse {
    @SerializedName("status")
    var status: Boolean = false

    @SerializedName("message")
    var message: String? = null
}