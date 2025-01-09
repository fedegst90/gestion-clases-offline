package com.fedegst90.gestionclasesapp.domine.model

import com.fedegst90.gestionclasesapp.core.toModel
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursos
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursosYEstudiantes
import com.fedegst90.gestionclasesapp.data.database.entity.CursoConEstudiantes

data class ColegioConCursosModel(
    val colegio: ColegioModel,
    val cursos: List<CursoModel>
)

data class CursoConEstudiantesModel(
    val curso: CursoModel,
    val estudiantes: List<EstudianteModel>
)

data class ColegioConCursosYEstudiantesModel(
    val colegio: ColegioModel,
    val cursos: List<CursoConEstudiantesModel>
)


fun ColegioConCursos.toDomain(): ColegioConCursosModel {
    return ColegioConCursosModel(
        colegio = colegio.toModel(),
        cursos = cursos.map { it.toModel() }
    )
}

fun CursoConEstudiantes.toDomain(): CursoConEstudiantesModel {
    return CursoConEstudiantesModel(
        curso = curso.toModel(),
        estudiantes = estudiantes.map { it.toModel() }
    )
}

fun ColegioConCursosYEstudiantes.toDomain(): ColegioConCursosYEstudiantesModel {
    return ColegioConCursosYEstudiantesModel(
        colegio = colegio.toModel(),
        cursos = cursos.map { it.toDomain() }
    )
}
