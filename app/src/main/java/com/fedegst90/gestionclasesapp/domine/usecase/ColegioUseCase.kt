package com.fedegst90.gestionclasesapp.domine.usecase

import com.fedegst90.gestionclasesapp.core.toEntity
import com.fedegst90.gestionclasesapp.core.toModel
import com.fedegst90.gestionclasesapp.domine.ColegioRepository
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
