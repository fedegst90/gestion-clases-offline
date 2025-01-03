package com.fedegst90.gestionclasesapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fedegst90.gestionclasesapp.data.entity.EstudianteEntity


@Dao
interface EstudianteDao {

    // Insertar un estudiante
    @Insert
    suspend fun insertStudent(student: EstudianteEntity)

    // Actualizar un estudiante
    @Update
    suspend fun updateStudent(student: EstudianteEntity)

    // Eliminar un estudiante
    @Delete
    suspend fun deleteStudent(student: EstudianteEntity)

    // Obtener todos los estudiantes
    @Query("SELECT * FROM estudiante_table")
    suspend fun getAllStudents(): List<EstudianteEntity>

    // Obtener un estudiante por su ID
    @Query("SELECT * FROM estudiante_table WHERE id = :studentId")
    suspend fun getStudentById(studentId: Int): EstudianteEntity?

    // Obtener todos los estudiantes por colegio
    @Query("SELECT * FROM estudiante_table WHERE colegio_id = :colegioId")
    suspend fun getStudentsByColegio(colegioId: Int): List<EstudianteEntity>

    // Obtener estudiantes por curso y división
    @Query("SELECT * FROM estudiante_table WHERE curso = :curso AND division = :division")
    suspend fun getStudentsByCursoAndDivision(curso: Int, division: String): List<EstudianteEntity>
}
