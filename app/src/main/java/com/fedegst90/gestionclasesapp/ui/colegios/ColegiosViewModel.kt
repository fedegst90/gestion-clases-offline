package com.fedegst90.gestionclasesapp.ui.colegios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel
import com.fedegst90.gestionclasesapp.domine.usecase.DeleteColegioUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.GetAllColegiosUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.GetColegioByIdUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.GetColegioByNameUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.InsertColegioUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.UpdateColegioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel

class ColegiosViewModel @Inject constructor(
    private val insertColegioUseCase: InsertColegioUseCase,
    private val updateColegioUseCase: UpdateColegioUseCase,
    private val deleteColegioUseCase: DeleteColegioUseCase,
    private val getAllColegiosUseCase: GetAllColegiosUseCase,
    private val getColegioByIdUseCase: GetColegioByIdUseCase,
    private val getColegioByNameUseCase: GetColegioByNameUseCase,

) : ViewModel() {

    private val dispatcherIO = Dispatchers.IO
    // LiveData para exponer los resultados
    private val _colegios = MutableLiveData<List<ColegioModel>>()
    val colegios: LiveData<List<ColegioModel>> get() = _colegios

    private val _selectedColegio = MutableLiveData<ColegioModel?>()
    val selectedColegio: LiveData<ColegioModel?> get() = _selectedColegio

    // Insertar colegio
    fun insertColegio(colegio: ColegioModel) {
        viewModelScope.launch {
            withContext(dispatcherIO) {
                insertColegioUseCase(colegio)

            }
        }

        // Actualizar colegio
        fun updateColegio(colegio: ColegioModel) {
            viewModelScope.launch {
                withContext(dispatcherIO) {
                    updateColegioUseCase(colegio)
                }
            }
        }

        // Eliminar colegio
        fun deleteColegio(colegio: ColegioModel) {
            viewModelScope.launch {
                withContext(dispatcherIO) {
                    deleteColegioUseCase(colegio)
                }
            }
        }

        // Obtener todos los colegios
        fun getAllColegios() {
            viewModelScope.launch {
                val result = withContext(dispatcherIO) {
                    getAllColegiosUseCase()
                }
                _colegios.postValue(result)
            }
        }

        // Obtener colegio por ID
        fun getColegioById(colegioId: Int) {
            viewModelScope.launch {
                val result = withContext(dispatcherIO) {
                    getColegioByIdUseCase(colegioId)
                }
                _selectedColegio.postValue(result)

            }
        }

        // Obtener colegio por nombre
        fun getColegioByName(nombre: String) {
            viewModelScope.launch {
                val result = withContext(dispatcherIO) {
                    getColegioByNameUseCase(nombre)
                }
                _selectedColegio.postValue(result)
            }
        }
    }
}
