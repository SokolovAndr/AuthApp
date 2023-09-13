package com.example.authapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.authapp.LatestMessagesActivity
import com.example.authapp.UserAdapter
import com.example.authapp.models.User

@Database(entities = [User::class], version = 1)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        fun getDb(context: Context): MainDb {
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "test2.db"
            ).build()
        }
    }
}