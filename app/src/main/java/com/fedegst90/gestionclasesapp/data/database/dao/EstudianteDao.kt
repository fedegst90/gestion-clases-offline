package com.fedegst90.gestionclasesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fedegst90.gestionclasesapp.data.database.entity.EstudiantesEntity


@Dao
interface EstudianteDao {

    // Insertar un estudiante
    @Insert
    suspend fun insertStudent(student: EstudiantesEntity)

    // Actualizar un estudiante
    @Update
    suspend fun updateStudent(student: EstudiantesEntity)

    // Eliminar un estudiante
    @Delete
    suspend fun deleteStudent(student: EstudiantesEntity)

    // Obtener todos los estudiantes
    @Query("SELECT * FROM estudiante_table")
    suspend fun getAllStudents(): List<EstudiantesEntity>

    // Obtener un estudiante por su ID
    @Query("SELECT * FROM estudiante_table WHERE id = :studentId")
    suspend fun getStudentById(studentId: Int): EstudiantesEntity?

    // Obtener todos los estudiantes por colegio
    @Query("SELECT * FROM estudiante_table WHERE colegio_id = :colegioId")
    suspend fun getStudentsByColegio(colegioId: Int): List<EstudiantesEntity>

    // Obtener estudiantes por curso y división
    @Query("SELECT * FROM estudiante_table WHERE curso_id = :curso")
    suspend fun getStudentsByCursoAndDivision(curso: Int): List<EstudiantesEntity>
}
