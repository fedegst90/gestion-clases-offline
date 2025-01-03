package com.fedegst90.gestionclasesapp.data

import com.fedegst90.gestionclasesapp.data.dao.ColegioDao
import com.fedegst90.gestionclasesapp.data.entity.ColegioEntity
import com.fedegst90.gestionclasesapp.domine.ColegioRepository
import javax.inject.Inject

class ColegioRepositoryImpl @Inject constructor(private val colegioDao: ColegioDao) :
    ColegioRepository {

    override suspend fun insertColegio(colegio: ColegioEntity) {
        colegioDao.insertColegio(colegio)
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
}