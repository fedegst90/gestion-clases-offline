package com.fedegst90.gestionclasesapp.domine

import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursos
import com.fedegst90.gestionclasesapp.data.database.entity.ColegiosEntity

interface ColegioRepository {

    suspend fun insertColegio(colegio: ColegiosEntity): Result<String>

    suspend fun updateColegio(colegio: ColegiosEntity)

    suspend fun deleteColegio(colegioId: Int)

    suspend fun getAllColegios(): List<ColegiosEntity>

    suspend fun getColegioById(colegioId: Int): ColegiosEntity?

    suspend fun getColegioByName(nombre: String): ColegiosEntity?

    suspend fun getAllColegioConCursos(): List<ColegioConCursos>

}

