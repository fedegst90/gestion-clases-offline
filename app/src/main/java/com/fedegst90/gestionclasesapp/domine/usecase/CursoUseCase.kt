package com.fedegst90.gestionclasesapp.domine.usecase

import com.fedegst90.gestionclasesapp.core.toEntity
import com.fedegst90.gestionclasesapp.core.toModel
import com.fedegst90.gestionclasesapp.data.database.entity.CursoConEstudiantes
import com.fedegst90.gestionclasesapp.domine.CursoRepository
import com.fedegst90.gestionclasesapp.domine.model.CursoModel
import javax.inject.Inject


// Caso de uso para insertar un curso
class InsertCursoUseCase @Inject constructor(private val cursosRepository: CursoRepository) {
    suspend operator fun invoke(curso: CursoModel) {
        cursosRepository.insert(curso.toEntity())
    }
}

// Caso de uso para actualizar un curso
class UpdateCursoUseCase @Inject constructor(private val cursosRepository: CursoRepository) {
    suspend operator fun invoke(curso: CursoModel) {
        cursosRepository.update(curso.toEntity())
    }
}

// Caso de uso para eliminar un curso
class DeleteCursoUseCase @Inject constructor(private val cursosRepository: CursoRepository) {
    suspend operator fun invoke(curso: CursoModel) {
        cursosRepository.delete(curso.toEntity())
    }
}

// Caso de uso para obtener todos los cursos
class GetAllCursosUseCase @Inject constructor(private val cursosRepository: CursoRepository) {
    suspend operator fun invoke(): List<CursoModel> {
        return cursosRepository.getAllCursos().map { it.toModel() }
    }
}

// Caso de uso para obtener un curso por su ID
class GetCursoByIdUseCase @Inject constructor(private val cursosRepository: CursoRepository) {
    suspend operator fun invoke(cursoId: Int): CursoModel? {
        return cursosRepository.getCursoById(cursoId)?.toModel()
    }
}

class GetCursoConEstudiantesUseCase @Inject constructor(
    private val cursoRepository: CursoRepository
) {
    suspend operator fun invoke(cursoId: Int): List<CursoConEstudiantes> {
        return cursoRepository.getCursoConEstudiantes(cursoId)
    }
}
