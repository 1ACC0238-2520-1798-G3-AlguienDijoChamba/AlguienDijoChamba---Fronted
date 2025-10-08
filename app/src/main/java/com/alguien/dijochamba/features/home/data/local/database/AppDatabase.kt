package com.alguien.dijochamba.features.home.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alguien.dijochamba.features.home.data.local.dao.ProductDao
import com.alguien.dijochamba.features.home.data.local.models.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}