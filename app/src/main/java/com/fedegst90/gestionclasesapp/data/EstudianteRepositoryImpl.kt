package com.fedegst90.gestionclasesapp.data

import com.fedegst90.gestionclasesapp.data.dao.EstudianteDao
import com.fedegst90.gestionclasesapp.data.entity.EstudianteEntity
import com.fedegst90.gestionclasesapp.domine.EstudianteRepository
import javax.inject.Inject

class EstudianteRepositoryImpl @Inject constructor(private val estudianteDao: EstudianteDao) :
    EstudianteRepository {

    override suspend fun insertStudent(student: EstudianteEntity) {
        estudianteDao.insertStudent(student)
    }

    override suspend fun updateStudent(student: EstudianteEntity) {
        estudianteDao.updateStudent(student)
    }

    override suspend fun deleteStudent(student: EstudianteEntity) {
        estudianteDao.deleteStudent(student)
    }

    override suspend fun getAllStudents(): List<EstudianteEntity> {
        return estudianteDao.getAllStudents()
    }

    override suspend fun getStudentById(studentId: Int): EstudianteEntity? {
        return estudianteDao.getStudentById(studentId)
    }

    override suspend fun getStudentsByColegio(colegioId: Int): List<EstudianteEntity> {
        return estudianteDao.getStudentsByColegio(colegioId)
    }

    override suspend fun getStudentsByCursoAndDivision(
        curso: Int,
        division: String
    ): List<EstudianteEntity> {
        return estudianteDao.getStudentsByCursoAndDivision(curso, division)
    }
}