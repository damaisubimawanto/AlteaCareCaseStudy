package com.damai.data.service

import com.damai.data.response.HomeResponse
import retrofit2.http.GET

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
interface HomeService {

    @GET("/v3/c9a2b598-9c93-4999-bd04-0194839ef2dc")
    suspend fun getHome(): HomeResponse
}