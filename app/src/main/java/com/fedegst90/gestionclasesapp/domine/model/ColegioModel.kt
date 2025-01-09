package com.fedegst90.gestionclasesapp.domine.model

data class ColegioModel(
    val id: Int = 0,
    val nombre: String,
    val nro: Int,
)

data class ColegioConCursosModel(
    val colegio: ColegioModel,
    val cursos: List<CursoModel>
)



