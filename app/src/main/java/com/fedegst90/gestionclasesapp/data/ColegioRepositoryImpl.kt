package com.fedegst90.gestionclasesapp.data

import com.fedegst90.gestionclasesapp.data.database.dao.ColegioDao
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursos
import com.fedegst90.gestionclasesapp.data.database.entity.ColegiosEntity
import com.fedegst90.gestionclasesapp.domine.ColegioRepository
import javax.inject.Inject

class ColegioRepositoryImpl @Inject constructor(private val colegioDao: ColegioDao) :
    ColegioRepository {

    override suspend fun insertColegio(colegio: ColegiosEntity) {
        val existe = colegioDao.existeColegioConCodigo(colegio.nro) > 0
        if (existe) {
            throw Exception("Ya existe un colegio con el número de código ${colegio.nro}")
        } else {
            colegioDao.insertColegio(colegio)
        }
    }

    override suspend fun updateColegio(colegio: ColegiosEntity) {
        colegioDao.updateColegio(colegio)
    }

    override suspend fun deleteColegio(colegio: ColegiosEntity) {
        colegioDao.deleteColegio(colegio)
    }

    override suspend fun getAllColegios(): List<ColegiosEntity> {
        return colegioDao.getAllColegios()
    }

    override suspend fun getColegioById(colegioId: Int): ColegiosEntity? {
        return colegioDao.getColegioById(colegioId)
    }

    override suspend fun getColegioByName(nombre: String): ColegiosEntity? {
        return colegioDao.getColegioByName(nombre)
    }

    override suspend fun getAllColegioConCursos(): List<ColegioConCursos> {
        return colegioDao.getAllColegioConCursos()
    }

}