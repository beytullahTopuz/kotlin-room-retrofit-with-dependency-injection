package com.t4zb.kotlinroomretrofitwithdi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalLorem(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "lorem") val lorem: String?
)
