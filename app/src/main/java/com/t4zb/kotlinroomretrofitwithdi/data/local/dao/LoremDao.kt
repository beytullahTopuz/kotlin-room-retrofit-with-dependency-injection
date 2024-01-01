package com.t4zb.kotlinroomretrofitwithdi.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.t4zb.kotlinroomretrofitwithdi.data.model.LocalLorem

@Dao
interface LoremDao {
    @Insert
    suspend fun insertAll(vararg lorem: LocalLorem): List<Long>

    @Query("SELECT * FROM LocalLorem")
    suspend fun getAllLorem(): List<LocalLorem>

    @Query("SELECT * FROM LocalLorem WHERE id = :mId")
    suspend fun getLoremById(mId: Int): LocalLorem?

    @Delete
    suspend fun delete(lorem: LocalLorem)
}