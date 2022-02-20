package com.mosaleh.sparkbanking.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "Transaction")
data class Transaction(
    @PrimaryKey
    val transactionId: Int = (100000000..500000000).random(),
    val senderAccountNumber: String,
    val receiverAccountNumber: String
)