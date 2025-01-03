package com.fedegst90.gestionclasesapp.domine.usecase

import com.fedegst90.gestionclasesapp.core.toEntity
import com.fedegst90.gestionclasesapp.core.toModel
import com.fedegst90.gestionclasesapp.domine.EstudianteRepository
import com.fedegst90.gestionclasesapp.domine.model.EstudianteModel
import javax.inject.Inject

class InsertEstudianteUseCase @Inject constructor(private val estudianteRepository: EstudianteRepository) {
    suspend operator fun invoke(estudiante: EstudianteModel) {
        estudianteRepository.insertStudent(estudiante.toEntity())
    }
}

class UpdateEstudianteUseCase @Inject constructor(private val estudianteRepository: EstudianteRepository) {
    suspend operator fun invoke(estudiante: EstudianteModel) {
        estudianteRepository.updateStudent(estudiante.toEntity())
    }
}

class DeleteEstudianteUseCase @Inject constructor(private val estudianteRepository: EstudianteRepository) {
    suspend operator fun invoke(estudiante: EstudianteModel) {
        estudianteRepository.deleteStudent(estudiante.toEntity())
    }
}

class GetAllEstudiantesUseCase @Inject constructor(private val estudianteRepository: EstudianteRepository) {
    suspend operator fun invoke(): List<EstudianteModel> {
        return estudianteRepository.getAllStudents().map { it.toModel() }
    }
}

class GetEstudianteByIdUseCase @Inject constructor(private val estudianteRepository: EstudianteRepository) {
    suspend operator fun invoke(estudianteId: Int): EstudianteModel? {
        return estudianteRepository.getStudentById(estudianteId)?.toModel()
    }
}

class GetEstudiantesByColegioUseCase @Inject constructor(private val estudianteRepository: EstudianteRepository) {
    suspend operator fun invoke(colegioId: Int): List<EstudianteModel> {
        return estudianteRepository.getStudentsByColegio(colegioId).map { it.toModel() }
    }
}

class GetEstudiantesByCursoAndDivisionUseCase @Inject constructor(private val estudianteRepository: EstudianteRepository) {
    suspend operator fun invoke(curso: Int, division: String): List<EstudianteModel> {
        return estudianteRepository.getStudentsByCursoAndDivision(curso, division).map { it.toModel() }
    }
}
