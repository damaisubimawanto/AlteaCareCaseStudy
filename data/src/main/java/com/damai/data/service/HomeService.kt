package com.damai.data.service

import com.damai.data.response.HomeResponse
import retrofit2.http.GET

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
interface HomeService {

    @GET("/v3/372dbe94-bf6e-4ece-87ce-d127cc6bbc8a")
    suspend fun getHome(): HomeResponse
}