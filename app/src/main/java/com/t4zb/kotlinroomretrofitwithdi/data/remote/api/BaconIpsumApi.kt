package com.t4zb.kotlinroomretrofitwithdi.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query

interface BaconIpsumApi {

    @GET("/api/")
    suspend fun getLoremApi(
        @Query("type") type: String,
        @Query("paras") paras: Int = 5,
        @Query("sentences") sentences: Int,
        @Query("start-with-lorem") startWithLorem: Boolean = true,
        @Query("format") format: String = "json"
    ): List<String>
}