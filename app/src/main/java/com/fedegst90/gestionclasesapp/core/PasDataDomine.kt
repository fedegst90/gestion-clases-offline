package com.fedegst90.gestionclasesapp.core

import com.fedegst90.gestionclasesapp.data.entity.ColegioEntity
import com.fedegst90.gestionclasesapp.data.entity.EstudianteEntity
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel
import com.fedegst90.gestionclasesapp.domine.model.EstudianteModel


fun ColegioEntity.toModel(): ColegioModel {
    return ColegioModel(
        id = this.id,
        nombre = this.nombre,
        nro = this.nro,
        colegio = this.colgio
    )
}

fun ColegioModel.toEntity(): ColegioEntity {
    return ColegioEntity(
        id = this.id,
        nombre = this.nombre,
        nro = this.nro,
        colgio = this.colegio
    )
}


fun EstudianteEntity.toModel(): EstudianteModel {
    return EstudianteModel(
        id = this.id,
        apellido = this.apellido,
        nombre = this.nombre,
        nroDoc = this.nroDoc,
        legajo = this.legajo,
        colegioId = this.colegio,
        curso = this.curso,
        division = this.division
    )
}

fun EstudianteModel.toEntity(): EstudianteEntity {
    return EstudianteEntity(
        id = this.id,
        apellido = this.apellido,
        nombre = this.nombre,
        nroDoc = this.nroDoc,
        legajo = this.legajo,
        colegio = this.colegioId,
        curso = this.curso,
        division = this.division
    )
}
