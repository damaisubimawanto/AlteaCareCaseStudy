package com.damai.data.model

/**
 * Created by damai.subimawanto on 7/10/2022.
 */
data class SearchDoctorsModel(
    val doctorName: String?,
    val hospitalIds: List<String>?,
    val specializationIds: List<String>?
)
