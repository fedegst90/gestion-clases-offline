package com.fedegst90.gestionclasesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fedegst90.gestionclasesapp.data.dao.ColegioDao
import com.fedegst90.gestionclasesapp.data.dao.EstudianteDao
import com.fedegst90.gestionclasesapp.data.entity.ColegioEntity
import com.fedegst90.gestionclasesapp.data.entity.EstudianteEntity

@Database(
    entities = [EstudianteEntity::class, ColegioEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DataBaseApp : RoomDatabase() {
    abstract fun getEstudienteDao(): EstudianteDao
    abstract fun getColegioDao(): ColegioDao

}