package com.t4zb.kotlinroomretrofitwithdi.data.remote.repo

import com.t4zb.kotlinroomretrofitwithdi.data.remote.api.BaconIpsumApi
import javax.inject.Inject

class BaconIpsumRepository @Inject constructor(
    private val baconIpsumApi: BaconIpsumApi
) {
    suspend fun getLoremApi(
        type: String,
        paras: Int = 5,
        sentences: Int,
        startWithLorem: Boolean = true,
        format: String = "json"
    ) : List<String> {
        return  baconIpsumApi.getLoremApi(type,paras,sentences,startWithLorem,format)
    }

}