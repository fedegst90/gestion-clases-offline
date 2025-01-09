package com.fedegst90.gestionclasesapp.domine

import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursos
import com.fedegst90.gestionclasesapp.data.database.entity.CursoConEstudiantes
import com.fedegst90.gestionclasesapp.data.database.entity.CursosEntity

interface CursoRepository {

    suspend fun insert(curso: CursosEntity)

    suspend fun getAllCursos(): List<CursosEntity>

    suspend fun getCursoById(cursoId: Int): CursosEntity?

    suspend fun update(curso: CursosEntity)

    suspend fun delete(curso: CursosEntity)

    suspend fun deleteAllCursos()

    suspend fun getCursoConEstudiantes(cursoId: Int): List<CursoConEstudiantes>
}