package com.t4zb.kotlinroomretrofitwithdi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.t4zb.kotlinroomretrofitwithdi.data.local.dao.LoremDao
import com.t4zb.kotlinroomretrofitwithdi.data.model.LocalLorem

@Database(entities = [LocalLorem::class], version = 1)
abstract class LoremDatabase : RoomDatabase(){
    abstract fun loremDao() : LoremDao
}