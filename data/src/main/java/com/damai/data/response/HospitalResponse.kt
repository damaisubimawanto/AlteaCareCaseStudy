package com.damai.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class HospitalResponse {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("image")
    var image: PhotoResponse? = null
}