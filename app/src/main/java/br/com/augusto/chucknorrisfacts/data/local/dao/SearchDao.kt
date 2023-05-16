package br.com.augusto.chucknorrisfacts.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.augusto.chucknorrisfacts.data.local.entity.SearchEntity

@Dao
interface SearchDao {
    @Query("SELECT * FROM Search ORDER BY date")
    fun getAll(): List<SearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search: SearchEntity)
}
