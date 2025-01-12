package com.fedegst90.gestionclasesapp.data

import com.fedegst90.gestionclasesapp.data.database.dao.CursoDao
import com.fedegst90.gestionclasesapp.data.database.entity.CursoConEstudiantes
import com.fedegst90.gestionclasesapp.data.database.entity.CursosEntity
import com.fedegst90.gestionclasesapp.domine.CursoRepository
import javax.inject.Inject

class CursoRepositoryImpl @Inject constructor(
    private val cursosDao: CursoDao
) : CursoRepository {

    override suspend fun insert(curso: CursosEntity) {
        try {
        cursosDao.insert(curso)
        }catch (e:Exception){
           throw Exception("Error al guardar curso: ${e.message}" ,e)
        }
    }

    override suspend fun getAllCursos(): List<CursosEntity> {
        return cursosDao.getAllCursos()
    }

    override suspend fun getCursoById(cursoId: Int): CursosEntity? {
        return cursosDao.getCursoById(cursoId)
    }

    override suspend fun update(curso: CursosEntity) {
        cursosDao.update(curso)
    }

    override suspend fun delete(curso: CursosEntity) {
        cursosDao.delete(curso)
    }

    override suspend fun deleteAllCursos() {
        cursosDao.deleteAllCursos()
    }

    override suspend fun getCursoConEstudiantes(cursoId: Int): List<CursoConEstudiantes> {
        return cursosDao.getCursoConEstudiantes(cursoId)
    }

    override suspend fun getAllCursoConEstudiantes(): List<CursoConEstudiantes> {
        return cursosDao.getAllCursoConEstudiantes()
    }
}