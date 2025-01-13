package com.fedegst90.gestionclasesapp.ui.cursos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedegst90.gestionclasesapp.data.database.entity.CursoConEstudiantes
import com.fedegst90.gestionclasesapp.domine.model.CursoModel
import com.fedegst90.gestionclasesapp.domine.usecase.DeleteCursoUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.GetAllCursosUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.GetCursoByIdUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.GetCursoConEstudiantesUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.InsertCursoUseCase
import com.fedegst90.gestionclasesapp.domine.usecase.UpdateCursoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CursosViewModel @Inject constructor(
    private val insertCursoUseCase: InsertCursoUseCase,
    private val updateCursoUseCase: UpdateCursoUseCase,
    private val deleteCursoUseCase: DeleteCursoUseCase,
    private val getAllCursosUseCase: GetAllCursosUseCase,
    private val getCursoByIdUseCase: GetCursoByIdUseCase,
    private val getCursoConEstudiantesUseCase: GetCursoConEstudiantesUseCase
) : ViewModel() {

    private val dispatcherIO = Dispatchers.IO

    // LiveData para exponer los resultados
    private val _cursos = MutableLiveData<List<CursoModel>>()
    val cursos: LiveData<List<CursoModel>> get() = _cursos

    private val _selectedCurso = MutableLiveData<CursoModel?>()
    val selectedCurso: LiveData<CursoModel?> get() = _selectedCurso

    private val _cursoConEstudiantes = MutableLiveData<List<CursoConEstudiantes>>()
    val cursoConEstudiantes: LiveData<List<CursoConEstudiantes>> get() = _cursoConEstudiantes

    // Obtener curso con estudiantes
    fun getCursoConEstudiantes(cursoId: Int) {
        viewModelScope.launch {
            val result = withContext(dispatcherIO) {
                getCursoConEstudiantesUseCase(cursoId)
            }
            _cursoConEstudiantes.postValue(result)
        }
    }

    private val _insertCursoResult = MutableLiveData<Result<String>>()
    val insertCursoResult get() = _insertCursoResult

    // Insertar curso
    fun insertCurso(curso: CursoModel) {
        viewModelScope.launch {
            withContext(dispatcherIO) {
                try {
                    insertCursoUseCase(curso)
                    _insertCursoResult.postValue(Result.success("Curso agegado correctamente"))
                } catch (e: Exception) {
                    _insertCursoResult.postValue(Result.failure(e))
                }
            }
        }
    }

    // Actualizar curso
    fun updateCurso(curso: CursoModel) {
        viewModelScope.launch {
            withContext(dispatcherIO) {
                updateCursoUseCase(curso)
            }
        }
    }
/*
    // Eliminar curso
    fun deleteCurso(curso: CursoModel) {
        viewModelScope.launch {
            withContext(dispatcherIO) {
                deleteCursoUseCase(curso)
            }
        }
    }
*/
    // Obtener todos los cursos
    fun getAllCursos() {
        viewModelScope.launch {
            val result = withContext(dispatcherIO) {
                getAllCursosUseCase()
            }
            _cursos.postValue(result)
        }
    }

    // Obtener curso por ID
    fun getCursoById(cursoId: Int) {
        viewModelScope.launch {
            val result = withContext(dispatcherIO) {
                getCursoByIdUseCase(cursoId)
            }
            _selectedCurso.postValue(result)
        }
    }
}

/*


class CursoViewModel @Inject constructor(
    private val insertCursoUseCase: InsertCursoUseCase,
    private val updateCursoUseCase: UpdateCursoUseCase,
    private val deleteCursoUseCase: DeleteCursoUseCase,
    private val getAllCursosUseCase: GetAllCursosUseCase,
    private val getCursoByIdUseCase: GetCursoByIdUseCase
) : ViewModel() {

    // LiveData para la lista de cursos
    private val _cursosList = MutableLiveData<List<CursoModel>>()
    val cursosList: LiveData<List<CursoModel>> get() = _cursosList

    // LiveData para el estado de carga (si estamos realizando una operación o no)
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    // LiveData para mostrar el resultado de una operación (éxito o error)
    private val _operationResult = MutableLiveData<String>()
    val operationResult: LiveData<String> get() = _operationResult

    // Función para insertar un curso
    fun insertCurso(curso: CursoModel) {
        _loading.value = true
        viewModelScope.launch {
            try {
                insertCursoUseCase(curso)
                _operationResult.value = "Curso insertado exitosamente"
                _loading.value = false
            } catch (e: Exception) {
                _operationResult.value = "Error al insertar el curso: ${e.message}"
                _loading.value = false
            }
        }
    }

    // Función para actualizar un curso
    fun updateCurso(curso: CursoModel) {
        _loading.value = true
        viewModelScope.launch {
            try {
                updateCursoUseCase(curso)
                _operationResult.value = "Curso actualizado exitosamente"
                _loading.value = false
            } catch (e: Exception) {
                _operationResult.value = "Error al actualizar el curso: ${e.message}"
                _loading.value = false
            }
        }
    }

    // Función para eliminar un curso
    fun deleteCurso(curso: CursoModel) {
        _loading.value = true
        viewModelScope.launch {
            try {
                deleteCursoUseCase(curso)
                _operationResult.value = "Curso eliminado exitosamente"
                _loading.value = false
            } catch (e: Exception) {
                _operationResult.value = "Error al eliminar el curso: ${e.message}"
                _loading.value = false
            }
        }
    }

    // Función para obtener todos los cursos
    fun getAllCursos() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val cursos = getAllCursosUseCase()
                _cursosList.value = cursos
                _loading.value = false
            } catch (e: Exception) {
                _operationResult.value = "Error al obtener los cursos: ${e.message}"
                _loading.value = false
            }
        }
    }

    // Función para obtener un curso por ID
    fun getCursoById(cursoId: Int) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val curso = getCursoByIdUseCase(cursoId)
                if (curso != null) {
                    _cursosList.value = listOf(curso)  // En este caso devuelvo una lista con un solo curso
                } else {
                    _operationResult.value = "Curso no encontrado"
                }
                _loading.value = false
            } catch (e: Exception) {
                _operationResult.value = "Error al obtener el curso: ${e.message}"
                _loading.value = false
            }
        }
    }
}
*/
