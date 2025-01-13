package com.fedegst90.gestionclasesapp.domine

import com.fedegst90.gestionclasesapp.data.database.entity.EstudiantesEntity

interface EstudianteRepository {

    suspend fun insertStudent(student: EstudiantesEntity)

    suspend fun updateStudent(student: EstudiantesEntity)

    suspend fun deleteStudentByColegio(colegioId: Int)

    suspend fun getAllStudents(): List<EstudiantesEntity>

    suspend fun getStudentById(studentId: Int): EstudiantesEntity?

    suspend fun getStudentsByColegio(colegioId: Int): List<EstudiantesEntity>

    suspend fun getStudentsByCursoAndDivision(curso: Int): List<EstudiantesEntity>
}