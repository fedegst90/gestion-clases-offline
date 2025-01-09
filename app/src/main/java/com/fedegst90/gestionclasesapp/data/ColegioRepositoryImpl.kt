package com.fedegst90.gestionclasesapp.data

import com.fedegst90.gestionclasesapp.data.database.dao.ColegioDao
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursos
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioEntity
import com.fedegst90.gestionclasesapp.domine.ColegioRepository
import javax.inject.Inject

class ColegioRepositoryImpl @Inject constructor(private val colegioDao: ColegioDao) :
    ColegioRepository {

    override suspend fun insertColegio(colegio: ColegioEntity) {
        val existe = colegioDao.existeColegioConCodigo(colegio.nro) > 0
        if (existe) {
            throw Exception("Ya existe un colegio con el número de código ${colegio.nro}")
        } else {
            colegioDao.insertColegio(colegio)
        }
    }

    override suspend fun updateColegio(colegio: ColegioEntity) {
        colegioDao.updateColegio(colegio)
    }

    override suspend fun deleteColegio(colegio: ColegioEntity) {
        colegioDao.deleteColegio(colegio)
    }

    override suspend fun getAllColegios(): List<ColegioEntity> {
        return colegioDao.getAllColegios()
    }

    override suspend fun getColegioById(colegioId: Int): ColegioEntity? {
        return colegioDao.getColegioById(colegioId)
    }

    override suspend fun getColegioByName(nombre: String): ColegioEntity? {
        return colegioDao.getColegioByName(nombre)
    }

    override suspend fun getAllColegioConCursos(): List<ColegioConCursos> {
        return colegioDao.getAllColegioConCursos()
    }

}