package com.fedegst90.gestionclasesapp.data

import com.fedegst90.gestionclasesapp.data.database.dao.EstudianteDao
import com.fedegst90.gestionclasesapp.data.database.entity.EstudiantesEntity
import com.fedegst90.gestionclasesapp.domine.EstudianteRepository
import javax.inject.Inject

class EstudianteRepositoryImpl @Inject constructor(
    private val estudianteDao: EstudianteDao
) :
    EstudianteRepository {

    override suspend fun insertStudent(student: EstudiantesEntity) {
        estudianteDao.insertStudent(student)
    }

    override suspend fun updateStudent(student: EstudiantesEntity) {
        estudianteDao.updateStudent(student)
    }

    override suspend fun deleteStudentByColegio(colegioId: Int) {
        estudianteDao.deleteStudentByColegio(colegioId)
    }

    override suspend fun getAllStudents(): List<EstudiantesEntity> {
        return estudianteDao.getAllStudents()
    }

    override suspend fun getStudentById(studentId: Int): EstudiantesEntity? {
        return estudianteDao.getStudentById(studentId)
    }

    override suspend fun getStudentsByColegio(colegioId: Int): List<EstudiantesEntity> {
        return estudianteDao.getStudentsByColegio(colegioId)
    }

    override suspend fun getStudentsByCursoAndDivision(
        curso: Int
    ): List<EstudiantesEntity> {
        return estudianteDao.getStudentsByCursoAndDivision(curso)
    }
}