package com.fedegst90.gestionclasesapp.core

import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursos
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursosYEstudiantes
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioEntity
import com.fedegst90.gestionclasesapp.data.database.entity.CursoConEstudiantes
import com.fedegst90.gestionclasesapp.data.database.entity.CursosEntity
import com.fedegst90.gestionclasesapp.data.database.entity.EstudianteEntity
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosModel
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosYEstudiantesModel
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel
import com.fedegst90.gestionclasesapp.domine.model.CursoConEstudiantesModel
import com.fedegst90.gestionclasesapp.domine.model.CursoModel
import com.fedegst90.gestionclasesapp.domine.model.EstudianteModel


fun ColegioEntity.toModel(): ColegioModel {
    return ColegioModel(
        id = this.id,
        nombre = this.nombre,
        nro = this.nro
    )
}

fun ColegioModel.toEntity(): ColegioEntity {
    return ColegioEntity(
        id = this.id,
        nombre = this.nombre,
        nro = this.nro,
    )
}


fun EstudianteEntity.toModel(): EstudianteModel {
    return EstudianteModel(
        id = this.id,
        apellido = this.apellido,
        nombre = this.nombre,
        nroDoc = this.nroDoc,
        legajo = this.legajo,
        colegioId = this.colegioId,
        cursoId = this.cursoId,
        sexo = sexo
    )
}

fun EstudianteModel.toEntity(): EstudianteEntity {
    return EstudianteEntity(
        id = this.id,
        apellido = this.apellido,
        nombre = this.nombre,
        nroDoc = this.nroDoc,
        legajo = this.legajo,
        colegioId = this.colegioId,
        cursoId = this.cursoId,
        sexo = sexo,
    )
}


fun CursosEntity.toModel(): CursoModel {
    return CursoModel(
        id = this.id,
        year = this.year,
        division = this.division,
        colegioId = this.colegioId
    )
}

fun CursoModel.toEntity(): CursosEntity {
    return CursosEntity(
        id = this.id,
        year = this.year,
        division = this.division,
        colegioId = this.colegioId
    )
}

//Relation data


fun ColegioConCursos.toDomain(): ColegioConCursosModel {
    return ColegioConCursosModel(
        colegio = colegio.toModel(),
        cursos = cursos.map { it.toModel() }
    )
}

fun CursoConEstudiantes.toModel(): CursoConEstudiantesModel {
    return CursoConEstudiantesModel(
        curso = curso.toModel(),
        estudiantes = estudiantes.map { it.toModel() }
    )
}

fun ColegioConCursosYEstudiantes.toModel(): ColegioConCursosYEstudiantesModel {
    return ColegioConCursosYEstudiantesModel(
        colegio = colegio.toModel(),
        cursos = cursos.map { it.toModel() }
    )
}

