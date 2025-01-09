package com.fedegst90.gestionclasesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursos
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioEntity

@Dao
interface ColegioDao {

    // Insertar un colegio
    @Insert
    suspend fun insertColegio(colegio: ColegioEntity)

    // Verifica  si existe colegio con el mismo numero
    @Query("SELECT COUNT(*) FROM colegio_table WHERE nro = :nro")
    suspend fun existeColegioConCodigo(nro: Int): Int


    // Actualizar un colegio
    @Update
    suspend fun updateColegio(colegio: ColegioEntity)

    // Eliminar un colegio
    @Delete
    suspend fun deleteColegio(colegio: ColegioEntity)

    // Obtener todos los colegios
    @Query("SELECT * FROM colegio_table")
    suspend fun getAllColegios(): List<ColegioEntity>

    // Obtener un colegio por ID
    @Query("SELECT * FROM colegio_table WHERE id = :colegioId")
    suspend fun getColegioById(colegioId: Int): ColegioEntity?

    // Obtener un colegio por nombre
    @Query("SELECT * FROM colegio_table WHERE nombre = :nombre")
    suspend fun getColegioByName(nombre: String): ColegioEntity?

    @Transaction
    @Query("SELECT * FROM colegio_table")
    suspend fun getAllColegioConCursos(): List<ColegioConCursos>

}
