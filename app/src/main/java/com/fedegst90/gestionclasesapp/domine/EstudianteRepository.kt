package com.fedegst90.gestionclasesapp.domine

import com.fedegst90.gestionclasesapp.data.entity.EstudianteEntity

interface EstudianteRepository {

    suspend fun insertStudent(student: EstudianteEntity)

    suspend fun updateStudent(student: EstudianteEntity)

    suspend fun deleteStudent(student: EstudianteEntity)

    suspend fun getAllStudents(): List<EstudianteEntity>

    suspend fun getStudentById(studentId: Int): EstudianteEntity?

    suspend fun getStudentsByColegio(colegioId: Int): List<EstudianteEntity>

    suspend fun getStudentsByCursoAndDivision(curso: Int, division: String): List<EstudianteEntity>
}