package com.damai.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class PhotoFormatResponse {

    @SerializedName("thumbnail")
    var thumbnail: String? = null

    @SerializedName("large")
    var large: String? = null

    @SerializedName("medium")
    var medium: String? = null

    @SerializedName("small")
    var small: String? = null
}