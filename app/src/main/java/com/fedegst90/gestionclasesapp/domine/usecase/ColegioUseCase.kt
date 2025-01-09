package com.fedegst90.gestionclasesapp.domine.usecase

import com.fedegst90.gestionclasesapp.core.toEntity
import com.fedegst90.gestionclasesapp.core.toModel
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursos
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursosYEstudiantes
import com.fedegst90.gestionclasesapp.data.database.entity.CursoConEstudiantes
import com.fedegst90.gestionclasesapp.domine.ColegioRepository
import com.fedegst90.gestionclasesapp.domine.CursoRepository
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel
import javax.inject.Inject

class InsertColegioUseCase @Inject constructor(private val colegioRepository: ColegioRepository) {
    suspend operator fun invoke(colegio: ColegioModel) {
        colegioRepository.insertColegio(colegio.toEntity())
    }
}

class UpdateColegioUseCase @Inject constructor(private val colegioRepository: ColegioRepository) {
    suspend operator fun invoke(colegio: ColegioModel) {
        colegioRepository.updateColegio(colegio.toEntity())
    }
}

class DeleteColegioUseCase @Inject constructor(private val colegioRepository: ColegioRepository) {
    suspend operator fun invoke(colegio: ColegioModel) {
        colegioRepository.deleteColegio(colegio.toEntity())
    }
}

class GetAllColegiosUseCase @Inject constructor(private val colegioRepository: ColegioRepository) {
    suspend operator fun invoke(): List<ColegioModel> {
        return colegioRepository.getAllColegios().map { it.toModel() }
    }
}

class GetColegioByIdUseCase @Inject constructor(private val colegioRepository: ColegioRepository) {
    suspend operator fun invoke(colegioId: Int): ColegioModel? {
        return colegioRepository.getColegioById(colegioId)?.toModel()
    }
}

class GetColegioByNameUseCase @Inject constructor(private val colegioRepository: ColegioRepository) {
    suspend operator fun invoke(nombre: String): ColegioModel? {
        return colegioRepository.getColegioByName(nombre)?.toModel()
    }
}

class GetAllColegioConCursosUseCase @Inject constructor(
    private val colegioRepository: ColegioRepository
) {
    suspend operator fun invoke(): List<ColegioConCursos> {
        return colegioRepository.getAllColegioConCursos()
    }
}

class GetAllColegiosConCursosConEstudiantesUseCase @Inject constructor(
    private val colegioRepository: ColegioRepository,
    private val cursoRepository: CursoRepository
) {

    // Función para obtener todos los colegios con sus cursos y sus estudiantes
    suspend operator fun invoke(): List<ColegioConCursosYEstudiantes> {
        // Obtener todos los colegios con sus cursos
        val colegiosConCursos = colegioRepository.getAllColegioConCursos()

        // Obtener todos los cursos con sus estudiantes
        val cursosConEstudiantes = cursoRepository.getAllCursoConEstudiantes()

        // Combinar los dos listados
        return colegiosConCursos.map { colegioConCursos ->
            // Para cada colegio, buscamos los cursos y sus estudiantes
            val cursosConEstudiantesParaEsteColegio = colegioConCursos.cursos.map { curso ->
                // Encontramos los estudiantes para este curso
                val estudiantesParaEsteCurso = cursosConEstudiantes
                    .firstOrNull { it.curso.id == curso.id }?.estudiantes ?: emptyList()

                // Creamos un objeto CursoConEstudiantes con los estudiantes asociados
                CursoConEstudiantes(curso, estudiantesParaEsteCurso)
            }

            // Devolvemos un objeto combinado
            ColegioConCursosYEstudiantes(
                colegioConCursos.colegio,
                cursosConEstudiantesParaEsteColegio
            )
        }
    }
}


