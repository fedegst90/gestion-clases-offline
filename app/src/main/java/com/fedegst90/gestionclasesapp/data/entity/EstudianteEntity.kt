package com.fedegst90.gestionclasesapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estudiante_table")
data class EstudianteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("apellido") val apellido: String,
    @ColumnInfo("nombre") val nombre: String,
    @ColumnInfo("nro_doc") val nroDoc: Int,
    @ColumnInfo("sexo") val sexo: Char,
    @ColumnInfo("legajo") val legajo: Int,
    @ColumnInfo("colegio_id") val colegio: Int,
    @ColumnInfo("curso_id") val cursoId: Int
)
