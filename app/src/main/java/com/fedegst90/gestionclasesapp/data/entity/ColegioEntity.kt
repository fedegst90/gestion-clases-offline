package com.fedegst90.gestionclasesapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colegio_table")
data class ColegioEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo("nro") val nro: Int,
    @ColumnInfo(name = "colegio") val colgio: String,
)