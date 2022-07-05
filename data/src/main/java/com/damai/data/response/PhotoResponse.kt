package com.damai.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class PhotoResponse {

    @SerializedName("size_formatted")
    var sizeFormatted: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("formats")
    var formats: PhotoFormatResponse? = null
}