package com.mosaleh.sparkbanking.data

import android.app.Application
import android.content.Context
import com.mosaleh.sparkbanking.model.Transaction
import com.mosaleh.sparkbanking.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Repository (private val dbInteraction: AppDatabase) {
    suspend fun addUser(user: User) {
        dbInteraction.usersDao().addUser(user)
    }

    suspend fun updateUser(user: User) {
        dbInteraction.usersDao().updateUser(user)
    }

    suspend fun getAllUsers(): List<User> {
        return dbInteraction.usersDao().getAllUsers()
    }

    suspend fun addTransaction(transaction:Transaction) {
        dbInteraction.transactionsDao().addTransaction(transaction)
    }

}