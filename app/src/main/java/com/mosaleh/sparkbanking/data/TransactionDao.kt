package com.mosaleh.sparkbanking.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mosaleh.sparkbanking.model.Transaction
import com.mosaleh.sparkbanking.model.User

@Dao
interface TransactionDao {
    @Insert
    suspend fun addTransaction (user: Transaction)

    @Update
    suspend fun updateTransaction (user: Transaction)

    @Query("select * from `Transaction`")
    suspend fun getAllTransaction():List<Transaction>
}