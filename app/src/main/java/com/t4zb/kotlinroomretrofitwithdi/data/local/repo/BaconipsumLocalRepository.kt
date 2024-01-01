package com.t4zb.kotlinroomretrofitwithdi.data.local.repo

import com.t4zb.kotlinroomretrofitwithdi.data.local.dao.LoremDao
import com.t4zb.kotlinroomretrofitwithdi.data.model.LocalLorem
import javax.inject.Inject

class BaconipsumLocalRepository @Inject constructor(
    private val loremDao: LoremDao
) {
    suspend fun getLoremById(id: Int) : LocalLorem? {
        return loremDao.getLoremById(id)
    }

    suspend fun insertLorem(localLorem: LocalLorem) {
        try {
            var result = loremDao.insertAll(localLorem)
        } catch (e: Exception) {
            println("insert exception : ${e.message}")
        }
    }

    suspend fun getAllLorem(): List<LocalLorem> {
        return loremDao.getAllLorem()
    }

    suspend fun deleteLorem(localLorem: LocalLorem) {
        loremDao.delete(localLorem)
    }
}