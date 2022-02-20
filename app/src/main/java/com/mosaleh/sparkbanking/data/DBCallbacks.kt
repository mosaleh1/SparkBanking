package com.mosaleh.sparkbanking.data

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mosaleh.sparkbanking.model.User
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DBCallbacks @Inject constructor(private val appDB : AppDatabase) : RoomDatabase.Callback() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
            GlobalScope.launch {
            appDB.usersDao().addUser(User("1560077744569","Mohamed Saleh",50000000f))
                appDB.usersDao().addUser(User("1560077744569","Mohamed Saleh",50000f))
                appDB.usersDao().addUser(User("1560044489645","Ahmed Saleh",80000f))
                appDB.usersDao().addUser(User("8451238546129","Abdulrahman Saleh",5880f))
                appDB.usersDao().addUser(User("7775520510685","Sarah Saleh",200000f))
                appDB.usersDao().addUser(User("1456481026518","Ali Gamal",5000f))
                appDB.usersDao().addUser(User("1056526842239","Zaki Omar",557f))
                appDB.usersDao().addUser(User("4826485223482","Mostafa Kareem",0f))
                appDB.usersDao().addUser(User("1519977526925","Ali Saleh",50f))
                appDB.usersDao().addUser(User("4825651628946","Gamal Ali",7000f))
                appDB.usersDao().addUser(User("7894565123848","Omar Saleh",550f))
                appDB.usersDao().addUser(User("4512568461268","Mahmoud Gunidy",500f))
                appDB.usersDao().addUser(User("1236489762643","Mohamed Salah",5000f))
                appDB.usersDao().addUser(User("1516556312385","Ahmed Seif",500000f))
                appDB.usersDao().addUser(User("8456126458984","Yasmin ALi",80000f))
                appDB.usersDao().addUser(User("7961354648987","Mohamed Said",20000f))
        }

    }
}