package com.fedegst90.gestionclasesapp.ui.cursos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fedegst90.gestionclasesapp.databinding.FragmentCursosBinding
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosYEstudiantesModel
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel
import com.fedegst90.gestionclasesapp.domine.model.CursoConEstudiantesModel
import com.fedegst90.gestionclasesapp.domine.model.CursoModel
import com.fedegst90.gestionclasesapp.domine.model.EstudianteModel
import com.fedegst90.gestionclasesapp.ui.colegios.ColegiosViewModel
import com.fedegst90.gestionclasesapp.ui.cursos.adapter.cursoscolegios.CursosColegiosAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CursosFragment : Fragment() {

    private var _binding: FragmentCursosBinding? = null
    private val binding get() = _binding!!

    private val viewmodelColegios: ColegiosViewModel by activityViewModels()
    private lateinit var cursosColegiosAdapter: CursosColegiosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCursosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewmodelColegios.getAllColegiosConCursosConEstudiantes()

        viewmodelColegios.colegioConCursosConEstudiantes.observe(viewLifecycleOwner){
            cursosColegiosAdapter = CursosColegiosAdapter(it)
            binding.rvColegios.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = cursosColegiosAdapter
            }


        }


    }
}

/*
       val colegiosConCursosYEstudiantes = listOf(
            ColegioConCursosYEstudiantesModel(
                colegio = ColegioModel(1, "Colegio Nacional", 123),
                cursos = listOf(
                    CursoConEstudiantesModel(
                        curso = CursoModel(101, "Matemáticas", "1° Año", 1, 1),
                        estudiantes = listOf(
                            EstudianteModel(1, "Juan", "Pérez", 35264248, "masculino", 123, 1, 101),
                            EstudianteModel(
                                2,
                                "María",
                                "Gómez",
                                35264248,
                                "masculino",
                                123,
                                1,
                                101
                            ),
                            EstudianteModel(
                                3,
                                "Luis",
                                "Martínez",
                                35264248,
                                "masculino",
                                123,
                                1,
                                101
                            )
                        )
                    ),
                    CursoConEstudiantesModel(
                        curso = CursoModel(101, "Matemáticas", "1° Año", 1, 1),
                        estudiantes = listOf(
                            EstudianteModel(1, "Juan", "Pérez", 35264248, "masculino", 123, 1, 101),
                            EstudianteModel(
                                2,
                                "María",
                                "Gómez",
                                35264248,
                                "masculino",
                                123,
                                1,
                                101
                            ),
                            EstudianteModel(
                                3,
                                "Luis",
                                "Martínez",
                                35264248,
                                "masculino",
                                123,
                                1,
                                101
                            )
                        )
                    ), CursoConEstudiantesModel(
                        curso = CursoModel(101, "Matemáticas", "1° Año", 1, 1),
                        estudiantes = listOf(
                            EstudianteModel(1, "Juan", "Pérez", 35264248, "masculino", 123, 1, 101),
                            EstudianteModel(
                                2,
                                "María",
                                "Gómez",
                                35264248,
                                "masculino",
                                123,
                                1,
                                101
                            ),
                            EstudianteModel(
                                3,
                                "Luis",
                                "Martínez",
                                35264248,
                                "masculino",
                                123,
                                1,
                                101
                            )
                        )
                    ), CursoConEstudiantesModel(
                        curso = CursoModel(101, "Matemáticas", "1° Año", 1, 1),
                        estudiantes = listOf(
                            EstudianteModel(1, "Juan", "Pérez", 35264248, "masculino", 123, 1, 101),
                            EstudianteModel(
                                2,
                                "María",
                                "Gómez",
                                35264248,
                                "masculino",
                                123,
                                1,
                                101
                            ),
                            EstudianteModel(
                                3,
                                "Luis",
                                "Martínez",
                                35264248,
                                "masculino",
                                123,
                                1,
                                101
                            )
                        )
                    ),
                    CursoConEstudiantesModel(
                        curso = CursoModel(102, "Historia", "1° Año", 1, 1),
                        estudiantes = listOf(
                            EstudianteModel(4, "Ana", "López", 35264248, "masculino", 123, 1, 102),
                            EstudianteModel(
                                5,
                                "Pedro",
                                "García",
                                35264248,
                                "masculino",
                                123,
                                1,
                                102
                            )
                        )
                    )
                )
            ),
            ColegioConCursosYEstudiantesModel(
                colegio = ColegioModel(2, "Instituto Técnico", 742),
                cursos = listOf(
                    CursoConEstudiantesModel(
                        curso = CursoModel(201, "Física", "2° Año", 2, 1),
                        estudiantes = listOf(
                            EstudianteModel(
                                6,
                                "Sofía",
                                "Romero",
                                35264248,
                                "masculino",
                                123,
                                2,
                                201
                            ),
                            EstudianteModel(7, "Carlos", "Ruiz", 35264248, "masculino", 123, 2, 201)
                        )
                    ),
                    CursoConEstudiantesModel(
                        curso = CursoModel(202, "Química", "2° Año", 2, 1),
                        estudiantes = listOf(
                            EstudianteModel(
                                8,
                                "Elena",
                                "Fernández",
                                35264248,
                                "masculino",
                                123,
                                2,
                                202
                            ),
                            EstudianteModel(
                                9,
                                "Tomás",
                                "Ortiz",
                                35264248,
                                "masculino",
                                123,
                                2,
                                202
                            ),
                            EstudianteModel(
                                10,
                                "Clara",
                                "Salas",
                                35264248,
                                "masculino",
                                123,
                                2,
                                202
                            )
                        )
                    )
                )
            )
        )




 */