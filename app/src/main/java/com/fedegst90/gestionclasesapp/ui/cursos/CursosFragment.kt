package com.fedegst90.gestionclasesapp.ui.cursos

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat.Type
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fedegst90.gestionclasesapp.R
import com.fedegst90.gestionclasesapp.core.makeGone
import com.fedegst90.gestionclasesapp.core.makeVisible
import com.fedegst90.gestionclasesapp.core.showToast
import com.fedegst90.gestionclasesapp.databinding.FragmentCursosBinding
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel
import com.fedegst90.gestionclasesapp.ui.colegios.ColegiosViewModel
import com.fedegst90.gestionclasesapp.ui.cursos.adapter.cursoscolegios.CursosColegiosAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CursosFragment : Fragment() {

    private var _binding: FragmentCursosBinding? = null
    private val binding get() = _binding!!

    private val viewmodelColegios: ColegiosViewModel by activityViewModels()
    private val viewmodelCursos: CursosViewModel by activityViewModels()
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

        viewmodelColegios.colegioConCursosConEstudiantes.observe(viewLifecycleOwner) {
            cursosColegiosAdapter = CursosColegiosAdapter(it) { onAddCursoSelected(it) }
            binding.rvColegios.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = cursosColegiosAdapter
            }
        }

        setupListener()
    }

    private fun onAddCursoSelected(it: Int) {
        dialogAddCurso()
    }


    private fun setupListener() {

    }

    @SuppressLint("MissingInflatedId")
    private fun dialogAddCurso() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_create, null)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            //.setCancelable(false)
            .create()

        dialog.setCanceledOnTouchOutside(false)
        val colorHint = ContextCompat.getColor(requireContext(), R.color.grey)
        val tvTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        tvTitle.apply {
            setText("Nuevo Curso")
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }
        val etYear = dialogView.findViewById<EditText>(R.id.etNombreColegio)
        etYear.apply {
            inputType = InputType.TYPE_CLASS_NUMBER
            setHintTextColor(colorHint)
            hint = "Año del curos"
            filters = arrayOf(InputFilter.LengthFilter(2))
        }
        val etDivision = dialogView.findViewById<EditText>(R.id.etNro)
        etDivision.apply {
            inputType = InputType.TYPE_CLASS_TEXT
            setHintTextColor(colorHint)
            hint = "Division"
            filters = arrayOf(InputFilter.LengthFilter(20))
        }
        val btnConfirmar = dialogView.findViewById<Button>(R.id.btnConfirmar)

        val etMateria = EditText(context).apply {
            hint = "Materia"
            id = View.generateViewId()
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }
        val colorOptions = listOf(
            "#FF0000", // Rojo
            "#00FF00", // Verde
            "#0000FF", // Azul
            "#FFFF00", // Amarillo
            "#FFA500", // Naranja
            "#800080",  // Morado
            "#FF0000", // Rojo
            "#00FF00", // Verde
            "#0000FF", // Azul
            "#FFFF00", // Amarillo
            "#FFA500", // Naranja
            "#800080",  // Morado
            "#FF0000", // Rojo
            "#00FF00", // Verde
            "#0000FF", // Azul
            "#FFFF00", // Amarillo
            "#FFA500", // Naranja
            "#800080"  // Morado
        )

        val gridLayout = GridLayout(requireContext()).apply {
            rowCount = 9
            columnCount = 9
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            makeGone()
        }
        var colorv = ""
        colorOptions.forEach { color ->
            val colorView = View(requireContext()).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 30.dpToPx()
                    height = 30.dpToPx()
                    setMargins(16, 16, 16, 16)
                }
                background = ContextCompat.getDrawable(
                    context,
                    R.drawable.circle_curso
                )
                setBackgroundColor(Color.parseColor(color))
                setOnClickListener {
                    colorv = color
                    tvTitle.setBackgroundColor(Color.parseColor(color)) }
            }
            gridLayout.addView(colorView)
        }

        val linearLayout = dialogView.findViewById<LinearLayout>(R.id.linearLayout)
        linearLayout.addView(etMateria, linearLayout.childCount - 1)
        linearLayout.addView(gridLayout, linearLayout.childCount - 1)

        etMateria.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                gridLayout.makeVisible()
            } else {
                gridLayout.makeGone()
            }
        }
        btnConfirmar.setOnClickListener {
            val year = etYear.text.toString()
            val division = etDivision.text.toString()
            val materia = etMateria.text.toString()
            if (year.isNotBlank() && division.isNotBlank() && materia.isNotBlank()) {
                // Procesar la selección
                dialog.dismiss()
            } else {
                requireContext().showToast("Debe completar todos los campos")
            }
        }
        dialog.show()
    }

    private fun Int.dpToPx(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
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