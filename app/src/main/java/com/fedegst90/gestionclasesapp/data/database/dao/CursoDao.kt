package com.fedegst90.gestionclasesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.fedegst90.gestionclasesapp.data.database.entity.CursoConEstudiantes
import com.fedegst90.gestionclasesapp.data.database.entity.CursosEntity

@Dao
interface CursoDao {

    // Insertar un curso
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(curso: CursosEntity)

    // Obtener todos los cursos
    @Query("SELECT * FROM cursos_table")
    fun getAllCursos(): List<CursosEntity>

    // Obtener un curso por ID
    @Query("SELECT * FROM cursos_table WHERE id = :cursoId")
    suspend fun getCursoById(cursoId: Int): CursosEntity?

    // Actualizar un curso
    @Update
    suspend fun update(curso: CursosEntity)

    // Eliminar un curso
    @Delete
    suspend fun delete(curso: CursosEntity)

    // Eliminar todos los cursos
    @Query("DELETE FROM cursos_table")
    suspend fun deleteAllCursos()

    @Transaction
    @Query("SELECT * FROM cursos_table WHERE id = :cursoId")
    suspend fun getCursoConEstudiantes(cursoId: Int): List<CursoConEstudiantes>

    @Transaction
    @Query("SELECT * FROM cursos_table")
    suspend fun getAllCursoConEstudiantes(): List<CursoConEstudiantes>
}