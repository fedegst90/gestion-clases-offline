package com.fedegst90.gestionclasesapp.ui.cursos

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fedegst90.gestionclasesapp.R
import com.fedegst90.gestionclasesapp.core.esconderTeclado
import com.fedegst90.gestionclasesapp.core.makeGone
import com.fedegst90.gestionclasesapp.core.makeVisible
import com.fedegst90.gestionclasesapp.core.showToast
import com.fedegst90.gestionclasesapp.databinding.DialogCreateBinding
import com.fedegst90.gestionclasesapp.databinding.FragmentCursosBinding
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosYEstudiantesModel
import com.fedegst90.gestionclasesapp.domine.model.CursoModel
import com.fedegst90.gestionclasesapp.ui.colegios.ColegiosViewModel
import com.fedegst90.gestionclasesapp.ui.cursos.adapter.cursoscolegios.CursosColegiosAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CursosFragment : Fragment() {

    private var _binding: FragmentCursosBinding? = null
    private val binding get() = _binding!!
    private val viewmodelColegios: ColegiosViewModel by viewModels()
    private val viewmodelCursos: CursosViewModel by activityViewModels()
    private lateinit var cursosColegiosAdapter: CursosColegiosAdapter
    private var colegio = 0
    private var listColegio = emptyList<ColegioConCursosYEstudiantesModel>()

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
        colegio = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        colegio = CursosFragmentArgs.fromBundle(requireArguments()).colegio


        setupListener()
        setupObserver()
        setupRecyclerView()
        setupIU()
    }

    private fun setupIU() {

        viewmodelColegios.getAllColegiosConCursosConEstudiantes()


    }

    private fun setupObserver() {
        viewmodelColegios.colegioConCursosConEstudiantes.observe(viewLifecycleOwner) {
            if (colegio != 0) {
                val lis = it.filter { list -> list.colegio.id == colegio }
                cursosColegiosAdapter.updateList(lis)
            } else {
                cursosColegiosAdapter.updateList(it)
            }
        }

        viewmodelCursos.insertCursoResult.observe(viewLifecycleOwner) {
            it.onSuccess { msg ->
                requireContext().showToast(msg)
                setupIU()
            }.onFailure { error ->
                requireContext().showToast(error.message.toString())
            }
        }
    }

    private fun setupRecyclerView() {
        cursosColegiosAdapter = CursosColegiosAdapter { onAddCursoSelected(it) }
        binding.rvColegios.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = cursosColegiosAdapter
        }
    }

    private fun onAddCursoSelected(id: Int) {
        dialogAddCurso(id)
    }


    private fun setupListener() {

    }

    private fun dialogAddCurso(colegioid: Int) {
        val dialogBinding = DialogCreateBinding.inflate(LayoutInflater.from(requireContext()))
        val dialog = createDialog(dialogBinding)
        val colorOptions = getColorOptions()
        var colorMateria = ""

        setupElement(dialogBinding)
        val etMateria = createEditTextMateria()

        val gridLayout = createColorGrid(colorOptions) { color ->
            colorMateria = color
            dialogBinding.tvTitle.setBackgroundColor(Color.parseColor(color))
            etMateria.esconderTeclado()
            etMateria.clearFocus()
        }

        dialogBinding.linearLayout.addView(etMateria, dialogBinding.linearLayout.childCount - 3)
        dialogBinding.linearLayout.addView(gridLayout, dialogBinding.linearLayout.childCount - 1)

        etMateria.setFocusChangeListener(gridLayout)

        dialogBinding.btnConfirmar.setOnClickListener {
            val year = dialogBinding.etPrimero.text.toString()
            val division = dialogBinding.etSegundo.text.toString()
            val materia = etMateria.text.toString()
            if (year.isNotBlank() && division.isNotBlank() && materia.isNotBlank() && colorMateria.isNotBlank()) {
                viewmodelCursos.insertCurso(
                    CursoModel(
                        0,
                        year,
                        division,
                        colegioid,
                        materia.uppercase(),
                        colorMateria,
                    )
                )
                dialog.dismiss()
            } else {
                requireContext().showToast("Debe completar todos los campos")
            }
        }
        dialog.show()
    }

    private fun createDialog(dialogBinding: DialogCreateBinding): AlertDialog {
        return AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .setCancelable(true) // Disable dialog cancel when tapping outside
            .create()
    }

    private fun getColorOptions(): List<String> {
        return listOf(
            "#FF5733", "#33FF57", "#3357FF", "#F333FF", "#FFC300", "#DAF7A6",
            "#900C3F", "#C70039", "#FF5733", "#4A69BD", "#6A0572", "#B91646",
            "#1273DE", "#60BFB3", "#616D6D", "#A93226", "#AF7AC5", "#5DADE2",
            "#7B241C", "#EC7063", "#28B463", "#D68910", "#E74C3C", "#2E4053",
            "#229954", "#1F618D", "#784212"
        )
    }

    private fun setupElement(dialogBinding: DialogCreateBinding) {
        with(dialogBinding) {
            tvTitle.apply {
                setText("Nuevo Curso")
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }
            etPrimero.apply {
                inputType = InputType.TYPE_CLASS_NUMBER
                hint = "Curso"
                setHintTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                filters = arrayOf(InputFilter.LengthFilter(2))
            }


            etSegundo.apply {
                inputType = InputType.TYPE_CLASS_TEXT
                hint = "Division"
                setHintTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                filters = arrayOf(InputFilter.LengthFilter(20))
            }
        }
    }

    private fun createEditTextMateria(): EditText {
        return EditText(context).apply {
            inputType = InputType.TYPE_CLASS_TEXT
            hint = "Materia"
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            filters = arrayOf(InputFilter.LengthFilter(13))
        }
    }

    private fun createColorGrid(
        colorOptions: List<String>, onColorSelected: (String) -> Unit
    ): GridLayout {
        val gridLayout = GridLayout(requireContext()).apply {
            rowCount = 9
            columnCount = 9
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            visibility = View.GONE // Initially hidden
        }

        colorOptions.forEach { color ->
            val colorView = View(requireContext()).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 30.dpToPx()
                    height = 30.dpToPx()
                    setMargins(16, 16, 16, 16)
                }
                setBackgroundColor(Color.parseColor(color))
                setOnClickListener {
                    onColorSelected(color)
                }
            }
            gridLayout.addView(colorView)
        }

        return gridLayout
    }

    private fun EditText.setFocusChangeListener(gridLayout: GridLayout) =
        setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) gridLayout.makeVisible()
            else gridLayout.makeGone()
        }


    private fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()


}


/*  private fun dialogAddCurso(colegioid: Int) {
      val dialogBinding = DialogCreateBinding.inflate(LayoutInflater.from(requireContext()))
      val dialog = AlertDialog.Builder(requireContext())
          .setView(dialogBinding.root)
          .create()
      dialog.setCanceledOnTouchOutside(false)

      val colorHint = ContextCompat.getColor(requireContext(), R.color.grey)
      val colorOptions = listOf(
          "#FF5733", "#33FF57", "#3357FF", "#F333FF", "#FFC300", "#DAF7A6",
          "#900C3F", "#C70039", "#FF5733", "#4A69BD", "#6A0572", "#B91646",
          "#1273DE", "#60BFB3", "#616D6D", "#A93226", "#AF7AC5", "#5DADE2",
          "#7B241C", "#EC7063", "#28B463", "#D68910", "#E74C3C", "#2E4053",
          "#229954", "#1F618D", "#784212"
      )
      var colorMateria = ""

      with(dialogBinding) {
          tvTitle.apply {
              setText("Nuevo Curso")
              setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
          }

          etPrimero.apply {
              inputType = InputType.TYPE_CLASS_NUMBER
              setHintTextColor(colorHint)
              hint = "Curso"
              filters = arrayOf(InputFilter.LengthFilter(2))
          }

          etSegundo.apply {
              inputType = InputType.TYPE_CLASS_TEXT
              setHintTextColor(colorHint)
              hint = "Division"
              filters = arrayOf(InputFilter.LengthFilter(20))
          }
      }


      val etMateria = EditText(context).apply {
          inputType = InputType.TYPE_CLASS_TEXT
          hint = "Materia"
          id = View.generateViewId()
          LinearLayout.LayoutParams(
              LinearLayout.LayoutParams.MATCH_PARENT,
              LinearLayout.LayoutParams.WRAP_CONTENT
          )
          setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
          filters = arrayOf(InputFilter.LengthFilter(13))
      }

      val gridLayout = GridLayout(requireContext()).apply {
          rowCount = 9
          columnCount = 9
          layoutParams = LinearLayout.LayoutParams(
              LinearLayout.LayoutParams.MATCH_PARENT,
              LinearLayout.LayoutParams.WRAP_CONTENT
          )
          makeGone()
      }

      colorOptions.forEach { color ->
          val colorView = View(requireContext()).apply {
              layoutParams = GridLayout.LayoutParams().apply {
                  width = 30.dpToPx()
                  height = 30.dpToPx()
                  setMargins(16, 16, 16, 16)
              }
              setBackgroundColor(Color.parseColor(color))
              setOnClickListener {
                  colorMateria = ""
                  colorMateria = color
                  dialogBinding.tvTitle.setBackgroundColor(Color.parseColor(color))
                  gridLayout.makeGone()
                  etMateria.apply {
                      esconderTeclado()
                      clearFocus()
                  }
              }
          }
          gridLayout.addView(colorView)
      }

      dialogBinding.linearLayout.apply {
          addView(etMateria, dialogBinding.linearLayout.childCount - 3)
          addView(gridLayout, dialogBinding.linearLayout.childCount - 1)
      }

      etMateria.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
          if (hasFocus) {
              gridLayout.makeVisible()
          } else {
              gridLayout.makeGone()
          }
      }

      dialogBinding.btnConfirmar.setOnClickListener {
          val year = dialogBinding.etPrimero.text.toString()
          val division = dialogBinding.etSegundo.text.toString()
          val materia = etMateria.text.toString()
          if (year.isNotBlank() && division.isNotBlank() && materia.isNotBlank() && colorMateria.isNotBlank()) {
              viewmodelCursos.insertCurso(
                  CursoModel(0, year, division, colegioid, materia, colorMateria)
              )
              dialog.dismiss()
          } else {
              requireContext().showToast("Debe completar todos los campos")
          }
      }
      dialog.show()
  }


}*/

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