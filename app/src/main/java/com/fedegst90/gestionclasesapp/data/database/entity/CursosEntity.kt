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
    @ColumnInfo("colegio_id") val colegioId: Int,
    @ColumnInfo("materia") val materia: String,
    @ColumnInfo("color") val color: String,
)