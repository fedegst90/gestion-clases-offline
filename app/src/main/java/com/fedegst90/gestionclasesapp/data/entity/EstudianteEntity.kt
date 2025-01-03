package com.fedegst90.gestionclasesapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estudiante_table")
data class EstudianteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo(name = "apellido") val apellido: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo("nro_doc") val nroDoc: Int,
    @ColumnInfo(name = "legajo") val legajo: Int,
    @ColumnInfo(name = "colegio_id") val colegio: Int,
    @ColumnInfo(name = "curso") val curso: Int,
    @ColumnInfo(name = "division") val division: String,
)
