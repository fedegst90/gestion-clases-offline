package com.fedegst90.gestionclasesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fedegst90.gestionclasesapp.data.database.dao.ColegioDao
import com.fedegst90.gestionclasesapp.data.database.dao.CursoDao
import com.fedegst90.gestionclasesapp.data.database.dao.EstudianteDao
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioEntity
import com.fedegst90.gestionclasesapp.data.database.entity.CursosEntity
import com.fedegst90.gestionclasesapp.data.database.entity.EstudianteEntity

@Database(
    entities = [EstudianteEntity::class, ColegioEntity::class, CursosEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DataBaseApp : RoomDatabase() {
    abstract fun getEstudienteDao(): EstudianteDao
    abstract fun getColegioDao(): ColegioDao
    abstract fun getCursoDao(): CursoDao
}