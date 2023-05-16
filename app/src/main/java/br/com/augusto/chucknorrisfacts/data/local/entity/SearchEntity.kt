package br.com.augusto.chucknorrisfacts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "search")
data class SearchEntity(
    @PrimaryKey
    val name: String,
    val date: LocalDateTime
)