package com.fedegst90.gestionclasesapp.domine.model


data class EstudianteModel(
    val id: Int = 0,
    val apellido: String,
    val nombre: String,
    val nroDoc: Int,
    val sexo: Char,
    val legajo: Int,
    val colegioId: Int,
    val cursoId: Int,
)