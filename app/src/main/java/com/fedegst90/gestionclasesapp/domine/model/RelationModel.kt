package com.fedegst90.gestionclasesapp.domine.model

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


