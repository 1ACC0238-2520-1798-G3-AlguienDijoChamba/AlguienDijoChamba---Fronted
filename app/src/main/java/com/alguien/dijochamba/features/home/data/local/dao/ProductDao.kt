package com.alguien.dijochamba.features.home.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alguien.dijochamba.features.home.data.local.models.ProductEntity

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(entity: ProductEntity)

    @Delete
    suspend fun delete(entity: ProductEntity)

    @Query("select * from products")
    suspend fun fetchAll(): List<ProductEntity>

    @Query("select * from products where id=:id")
    suspend fun fetchProductById(id: Int): List<ProductEntity>

}