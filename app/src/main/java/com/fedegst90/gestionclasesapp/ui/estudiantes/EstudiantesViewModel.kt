package com.fedegst90.gestionclasesapp.ui.estudiantes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedegst90.gestionclasesapp.domine.model.EstudianteModel
import com.fedegst90.gestionclasesapp.domine.usecase.DeleteEstudianteUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.GetAllEstudiantesUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.GetEstudianteByIdUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.GetEstudiantesByColegioUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.GetEstudiantesByCursoAndDivisionUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.InsertEstudianteUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.UpdateEstudianteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel

class EstudiantesViewModel @Inject constructor(
    private val insertEstudianteUseCase: InsertEstudianteUseCase,
    private val updateEstudianteUseCase: UpdateEstudianteUseCase,
    private val deleteEstudianteUseCase: DeleteEstudianteUseCase,
    private val getAllEstudiantesUseCase: GetAllEstudiantesUseCase,
    private val getEstudianteByIdUseCase: GetEstudianteByIdUseCase,
    private val getEstudiantesByColegioUseCase: GetEstudiantesByColegioUseCase,
    private val getEstudiantesByCursoAndDivisionUseCase: GetEstudiantesByCursoAndDivisionUseCase
) : ViewModel() {

    private val dispatcherIO = Dispatchers.IO

    // LiveData para exponer los resultados
    private val _estudiantes = MutableLiveData<List<EstudianteModel>>()
    val estudiantes: LiveData<List<EstudianteModel>> get() = _estudiantes

    private val _selectedEstudiante = MutableLiveData<EstudianteModel?>()
    val selectedEstudiante: LiveData<EstudianteModel?> get() = _selectedEstudiante

    // Insertar estudiante
    fun insertEstudiante(estudiante: EstudianteModel) {
        viewModelScope.launch {
            withContext(dispatcherIO) {
                insertEstudianteUseCase(estudiante)
            }
        }
    }

    // Actualizar estudiante
    fun updateEstudiante(estudiante: EstudianteModel) {
        viewModelScope.launch {
            withContext(dispatcherIO) {
                updateEstudianteUseCase(estudiante)
            }
        }
    }

    // Eliminar estudiante
    fun deleteEstudiante(estudiante: EstudianteModel) {
        viewModelScope.launch {
            withContext(dispatcherIO) {
                deleteEstudianteUseCase(estudiante)
            }
        }
    }

    // Obtener todos los estudiantes
    fun getAllEstudiantes() {
        viewModelScope.launch {
            val result = withContext(dispatcherIO) {
                getAllEstudiantesUseCase()
            }
            _estudiantes.postValue(result)
        }
    }

    // Obtener estudiante por ID
    fun getEstudianteById(estudianteId: Int) {
        viewModelScope.launch {
            val result = withContext(dispatcherIO) {
                getEstudianteByIdUseCase(estudianteId)
            }
            _selectedEstudiante.postValue(result)
        }
    }

    // Obtener estudiantes por colegio
    fun getEstudiantesByColegio(colegioId: Int) {
        viewModelScope.launch {
            val result = withContext(dispatcherIO) {
                getEstudiantesByColegioUseCase(colegioId)
            }
            _estudiantes.postValue(result)
        }
    }

    // Obtener estudiantes por curso y división
    fun getEstudiantesByCursoAndDivision(curso: Int, division: String) {
        viewModelScope.launch {
            val result = withContext(dispatcherIO) {
                getEstudiantesByCursoAndDivisionUseCase(curso, division)
            }
            _estudiantes.postValue(result)
        }
    }
}
