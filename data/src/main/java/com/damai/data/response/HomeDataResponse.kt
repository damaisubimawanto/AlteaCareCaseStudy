package com.damai.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class HomeDataResponse {

    @SerializedName("doctor_id")
    var doctorId: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("slug")
    var slug: String? = null

    @SerializedName("is_popular")
    var isPopular: Boolean = false

    @SerializedName("about")
    var about: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("photo")
    var photo: PhotoResponse? = null

    @SerializedName("sip")
    var sip: String? = null

    @SerializedName("experience")
    var experience: String? = null

    @SerializedName("price")
    var price: PriceResponse? = null

    @SerializedName("specialization")
    var specialization: SpecializationResponse? = null

    @SerializedName("hospital")
    var hospital: List<HospitalResponse>? = null

    @SerializedName("about_preview")
    var aboutPreview: String? = null
}