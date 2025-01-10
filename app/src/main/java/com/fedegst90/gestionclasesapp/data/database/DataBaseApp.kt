package com.fedegst90.gestionclasesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fedegst90.gestionclasesapp.data.database.dao.ColegioDao
import com.fedegst90.gestionclasesapp.data.database.dao.CursoDao
import com.fedegst90.gestionclasesapp.data.database.dao.EstudianteDao
import com.fedegst90.gestionclasesapp.data.database.entity.ColegiosEntity
import com.fedegst90.gestionclasesapp.data.database.entity.CursosEntity
import com.fedegst90.gestionclasesapp.data.database.entity.EstudiantesEntity

@Database(
    entities = [EstudiantesEntity::class, ColegiosEntity::class, CursosEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DataBaseApp : RoomDatabase() {
    abstract fun getEstudienteDao(): EstudianteDao
    abstract fun getColegioDao(): ColegioDao
    abstract fun getCursoDao(): CursoDao
}