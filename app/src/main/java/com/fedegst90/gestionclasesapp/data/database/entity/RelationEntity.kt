package com.fedegst90.gestionclasesapp.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ColegioConCursos(
    @Embedded val colegio: ColegioEntity,
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
    val estudiantes: List<EstudianteEntity>
)

data class ColegioConCursosYEstudiantes(
    @Embedded val colegio: ColegioEntity,
    val cursos: List<CursoConEstudiantes>
)