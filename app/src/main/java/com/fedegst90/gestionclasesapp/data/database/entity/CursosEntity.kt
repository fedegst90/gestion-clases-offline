package com.fedegst90.gestionclasesapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cursos_table")
data class CursosEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("año") val year: String,
    @ColumnInfo("division") val division: String,
    @ColumnInfo("escuela_id") val escuelaId: Int
)