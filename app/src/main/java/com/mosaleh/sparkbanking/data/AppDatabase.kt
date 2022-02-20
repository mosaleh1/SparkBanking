package com.mosaleh.sparkbanking.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.mosaleh.sparkbanking.model.Transaction
import com.mosaleh.sparkbanking.model.User

@Database(entities = [User::class,Transaction::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao():UsersDao

    abstract fun transactionsDao ():TransactionDao
}