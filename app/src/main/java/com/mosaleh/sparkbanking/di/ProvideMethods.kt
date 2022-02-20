package com.mosaleh.sparkbanking.di

import android.app.Application
import android.content.ContentValues
import android.content.Context
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mosaleh.sparkbanking.data.AppDatabase
import com.mosaleh.sparkbanking.data.Repository
import com.mosaleh.sparkbanking.data.UsersDao
import com.mosaleh.sparkbanking.model.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@InstallIn(SingletonComponent::class)
@Module
object ProvideMethods {

    @OptIn(DelicateCoroutinesApi::class)
    @Provides
    fun provideDatabaseClass(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    GlobalScope.launch {
                        val list = ArrayList<User>()
                        list.add(User("15600777445691", "Mohamed Saleh", 50000000f))
                        list.add(User("15600777445692", "Mohamed Saleh", 50000f))
                        list.add(User("15600444896453", "Ahmed Saleh", 80000f))
                        list.add(User("84512385461294", "Abdulrahman Saleh", 5880f))
                        list.add(User("77755205106855", "Sarah Saleh", 200000f))
                        list.add(User("14564810265186", "Ali Gamal", 5000f))
                        list.add(User("10565268422397", "Zaki Omar", 557f))
                        list.add(User("48264852234828", "Mostafa Kareem", 0f))
                        list.add(User("15199775269259", "Ali Saleh", 50f))
                        list.add(User("48256516289461", "Gamal Ali", 7000f))
                        list.add(User("78945651238482", "Omar Saleh", 550f))
                        list.add(User("45125684612683", "Mahmoud Gunidy", 500f))
                        list.add(User("12364897626434", "Mohamed Salah", 5000f))
                        list.add(User("15165563123855", "Ahmed Seif", 500000f))
                        list.add(User("84561264589846", "Yasmin ALi", 80000f))
                        list.add(User("79613546489877", "Mohamed Said", 20000f))
                        val contentValues = ContentValues()

                        for (user in list) {
                            contentValues.put("AccountNumber", user.AccountNumber)
                            contentValues.put("userName", user.userName)
                            contentValues.put("balance", user.balance)
                            db.insert("Users", OnConflictStrategy.ABORT, contentValues)
                        }
                    }
                }
            })
            .build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UsersDao {
        return appDatabase.usersDao()
    }

    @Provides
    fun provideRepository(usersDao: AppDatabase): Repository {
        return Repository(usersDao)
    }
}


