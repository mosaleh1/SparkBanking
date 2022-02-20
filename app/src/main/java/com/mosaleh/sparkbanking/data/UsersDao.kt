package com.mosaleh.sparkbanking.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mosaleh.sparkbanking.model.User

@Dao
interface UsersDao {

    @Insert
    suspend fun addUser (user: User)

    @Update
    suspend fun updateUser (user: User)

    @Query("select * from Users")
    suspend fun getAllUsers():List<User>
}