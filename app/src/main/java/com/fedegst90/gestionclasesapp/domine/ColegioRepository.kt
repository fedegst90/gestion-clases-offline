package com.fedegst90.gestionclasesapp.domine

import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursos
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioEntity
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosModel

interface ColegioRepository {

    suspend fun insertColegio(colegio: ColegioEntity)

    suspend fun updateColegio(colegio: ColegioEntity)

    suspend fun deleteColegio(colegio: ColegioEntity)

    suspend fun getAllColegios(): List<ColegioEntity>

    suspend fun getColegioById(colegioId: Int): ColegioEntity?

    suspend fun getColegioByName(nombre: String): ColegioEntity?

    suspend fun getAllColegioConCursos(): List<ColegioConCursos>

}

