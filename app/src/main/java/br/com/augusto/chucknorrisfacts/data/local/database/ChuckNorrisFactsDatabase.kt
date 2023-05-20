package br.com.augusto.chucknorrisfacts.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.augusto.chucknorrisfacts.data.local.converter.LocalDateTimeConverter
import br.com.augusto.chucknorrisfacts.data.local.dao.CategoryDao
import br.com.augusto.chucknorrisfacts.data.local.dao.SearchDao
import br.com.augusto.chucknorrisfacts.data.local.entity.CategoryEntity
import br.com.augusto.chucknorrisfacts.data.local.entity.SearchEntity

@Database(version = 1, entities = [SearchEntity::class, CategoryEntity::class])
@TypeConverters(LocalDateTimeConverter::class)
abstract class ChuckNorrisFactsDatabase : RoomDatabase() {

    abstract fun getSearchDao(): SearchDao

    abstract fun getCategoryDao(): CategoryDao
}
