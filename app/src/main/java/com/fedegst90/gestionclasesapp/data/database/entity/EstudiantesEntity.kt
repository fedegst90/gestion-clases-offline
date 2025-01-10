package com.fedegst90.gestionclasesapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estudiante_table")
data class EstudiantesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("apellido") val apellido: String,
    @ColumnInfo("nombre") val nombre: String,
    @ColumnInfo("nro_doc") val nroDoc: Int,
    @ColumnInfo("sexo") val sexo: String,
    @ColumnInfo("legajo") val legajo: Int,
    @ColumnInfo("colegio_id") val colegioId: Int,
    @ColumnInfo("curso_id") val cursoId: Int
)
