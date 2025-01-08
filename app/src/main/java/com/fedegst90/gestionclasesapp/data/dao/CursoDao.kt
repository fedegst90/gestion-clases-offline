package com.fedegst90.gestionclasesapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.fedegst90.gestionclasesapp.data.entity.ColegioConCursos
import com.fedegst90.gestionclasesapp.data.entity.CursoConEstudiantes

@Dao
interface CursoDao {
    @Transaction
    @Query("SELECT * FROM colegio_table WHERE id = :colegioId")
    suspend fun getColegioConCursos(colegioId: Int): List<ColegioConCursos>

    @Transaction
    @Query("SELECT * FROM cursos_table WHERE id = :cursoId")
    suspend fun getCursoConEstudiantes(cursoId: Int): List<CursoConEstudiantes>
}