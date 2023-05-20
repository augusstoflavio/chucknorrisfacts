package br.com.augusto.chucknorrisfacts.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.augusto.chucknorrisfacts.data.local.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category")
    suspend fun getAll(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categories: List<CategoryEntity>)
}
