package com.mosaleh.sparkbanking.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "Users")
@Parcelize
data class User(
    @PrimaryKey
    val AccountNumber: String,
    val userName: String,
    var balance: Float
): Parcelable