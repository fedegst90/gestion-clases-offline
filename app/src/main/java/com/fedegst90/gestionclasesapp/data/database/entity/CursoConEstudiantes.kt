package com.fedegst90.gestionclasesapp.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CursoConEstudiantes(
    @Embedded val curso: CursosEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "curso_id"
    )
    val estudiantes: List<EstudianteEntity>
)
