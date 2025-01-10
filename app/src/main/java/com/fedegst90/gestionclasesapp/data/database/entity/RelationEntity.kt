package com.fedegst90.gestionclasesapp.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ColegioConCursos(
    @Embedded val colegio: ColegiosEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "colegio_id"
    )
    val cursos: List<CursosEntity>
)

data class CursoConEstudiantes(
    @Embedded val curso: CursosEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "curso_id"
    )
    val estudiantes: List<EstudiantesEntity>
)

data class ColegioConCursosYEstudiantes(
    @Embedded val colegio: ColegiosEntity,
    val cursos: List<CursoConEstudiantes>
)