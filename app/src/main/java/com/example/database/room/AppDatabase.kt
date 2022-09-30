package com.example.database.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Player::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao
}