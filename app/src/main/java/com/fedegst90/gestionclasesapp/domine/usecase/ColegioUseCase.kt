package com.fedegst90.gestionclasesapp.domine.usecase

import com.fedegst90.gestionclasesapp.core.toEntity
import com.fedegst90.gestionclasesapp.core.toModel
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursos
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursosYEstudiantes
import com.fedegst90.gestionclasesapp.data.database.entity.CursoConEstudiantes
import com.fedegst90.gestionclasesapp.domine.ColegioRepository
import com.fedegst90.gestionclasesapp.domine.CursoRepository
import com.fedegst90.gestionclasesapp.domine.EstudianteRepository
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosYEstudiantesModel
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel
import javax.inject.Inject

class InsertColegioUseCase @Inject constructor(private val colegioRepository: ColegioRepository) {
    suspend operator fun invoke(colegio: ColegioModel): Result<String> {
        return colegioRepository.insertColegio(colegio.toEntity())
    }
}

class UpdateColegioUseCase @Inject constructor(private val colegioRepository: ColegioRepository) {
    suspend operator fun invoke(colegio: ColegioModel) {
        colegioRepository.updateColegio(colegio.toEntity())
    }
}

class DeleteColegioConCursosConEstudiantesUseCase @Inject constructor(
    private val colegioRepository: ColegioRepository,
    private val cursoRepository: CursoRepository,
    private val estudianteRepository: EstudianteRepository
) {
    suspend operator fun invoke(colegioId: Int) {
        colegioRepository.deleteColegio(colegioId)
        cursoRepository.deleteCursosByColegioId(colegioId)
        estudianteRepository.deleteStudentByColegio(colegioId)
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
    suspend operator fun invoke(): List<ColegioConCursosYEstudiantesModel> {

        val colegiosConCursos = colegioRepository.getAllColegioConCursos()
        val cursosConEstudiantes = cursoRepository.getAllCursoConEstudiantes()

        return colegiosConCursos.map { colegioConCursos ->

            val cursosConEstudiantesParaEsteColegio = colegioConCursos.cursos.map { curso ->

                val estudiantesParaEsteCurso = cursosConEstudiantes
                    .firstOrNull { it.curso.id == curso.id }?.estudiantes ?: emptyList()

                CursoConEstudiantes(curso, estudiantesParaEsteCurso)
            }

            ColegioConCursosYEstudiantes(
                colegioConCursos.colegio,
                cursosConEstudiantesParaEsteColegio
            ).toModel()
        }
    }
}


