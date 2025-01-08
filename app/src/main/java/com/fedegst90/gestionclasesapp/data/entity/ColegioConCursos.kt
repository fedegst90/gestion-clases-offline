package com.fedegst90.gestionclasesapp.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ColegioConCursos(
    @Embedded val colegio: ColegioEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "escuela_id"
    )
    val cursos: List<CursosEntity>
)
