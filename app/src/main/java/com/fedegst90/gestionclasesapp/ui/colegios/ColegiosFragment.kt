package com.fedegst90.gestionclasesapp.ui.colegios


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListPopupWindow
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fedegst90.gestionclasesapp.R
import com.fedegst90.gestionclasesapp.core.makeViewsInvisible
import com.fedegst90.gestionclasesapp.core.makeViewsVisible
import com.fedegst90.gestionclasesapp.core.showToast
import com.fedegst90.gestionclasesapp.databinding.FragmentColegiosBinding
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosYEstudiantesModel
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel
import com.fedegst90.gestionclasesapp.ui.colegios.adapter.ColegiosAdapter
import com.fedegst90.gestionclasesapp.ui.cursos.CursosViewModel
import com.fedegst90.gestionclasesapp.ui.estudiantes.EstudiantesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColegiosFragment : Fragment() {

    private var _binding: FragmentColegiosBinding? = null
    private val binding get() = _binding!!
    private val viewModelColegios: ColegiosViewModel by viewModels()
    private val viewModelEstudiantes: EstudiantesViewModel by viewModels()
    private val viewModelCursos: CursosViewModel by viewModels()
    private lateinit var adapterColegio: ColegiosAdapter
    private lateinit var popupAdapter: ArrayAdapter<String>
    private lateinit var listPopupWindow: ListPopupWindow
    private var listColegioModel: List<ColegioConCursosYEstudiantesModel> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentColegiosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupRecyclerView()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupIU()
        setupListener()
        setupObserver()
        setupSearchView()
        setupPopup()
    }

    private fun setupIU() {
        viewModelColegios.getAllColegiosConCursosConEstudiantes()
        viewModelEstudiantes.getAllEstudiantes()
        viewModelCursos.getAllCursos()
    }

    private fun setupObserver() {
        viewModelColegios.colegioConCursosConEstudiantes.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                adapterColegio.updateList(listOf())
                makeViewsInvisible(binding.imgLeft, binding.imgRight)
            } else {
                listColegioModel = it
                makeViewsVisible(binding.imgLeft, binding.imgRight)
                adapterColegio.updateList(it)
            }
            binding.includeItemColegio.tvCantidadColegios.text = it.size.toString()
        }

        viewModelCursos.cursos.observe(viewLifecycleOwner) {
            binding.includeItemColegio.tvCantidadCursos.text = it.size.toString()
        }

        viewModelEstudiantes.estudiantes.observe(viewLifecycleOwner) {
            binding.includeItemColegio.tvCantidadEstudiantes.text = it.size.toString()
        }

        viewModelColegios.insertColegioResult.observe(viewLifecycleOwner) { result ->
            val msg = result.fold(
                onSuccess = { it },
                onFailure = { it.message ?: "Error al agregar Colegio" })
            requireContext().showToast(msg)
        }

    }

    private fun setupListener() {
        binding.btnAddColegio.setOnClickListener {
            dialogNewColegio()
        }
    }

    private fun setupRecyclerView() {
        adapterColegio = ColegiosAdapter(
            colegiosList = listColegioModel,
            onCursoSelected = { colegioId -> onCursoSelected(colegioId) },
            onEstudianteSelected = { cursoId -> onEstudianteSelected(cursoId) }
        )
        binding.rvColegios.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterColegio
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filterList = filterItems(newText ?: "")
                adapterColegio.updateList(filterList)
                updatePopupAdapter(filterList)
                return true
            }
        })
    }

    private fun setupPopup() {
        listPopupWindow = ListPopupWindow(requireContext())
        listPopupWindow.anchorView = binding.searchView
        popupAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            listColegioModel.toMutableList().map { it.colegio.nombre })

        listPopupWindow.setAdapter(popupAdapter)


        listPopupWindow.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = popupAdapter.getItem(position)
            binding.searchView.setQuery(selectedItem.toString(), false)
            listPopupWindow.dismiss()
        }
    }

    private fun updatePopupAdapter(filterList: List<ColegioConCursosYEstudiantesModel>) {
        popupAdapter.clear()
        popupAdapter.addAll(filterList.map { it.colegio.nombre })
        if (filterList.isNotEmpty()) {
            listPopupWindow.show()
        } else {
            listPopupWindow.dismiss()
        }
    }

    private fun filterItems(query: String): List<ColegioConCursosYEstudiantesModel> =
        listColegioModel.filter { it.colegio.nombre.contains(query, ignoreCase = true) }

    @SuppressLint("MissingInflatedId")
    private fun dialogNewColegio() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_create, null)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            //.setCancelable(false)
            .create()

        dialog.setCanceledOnTouchOutside(false)

        val tvTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        tvTitle.setText("Nuevo Colegio")
        val etName = dialogView.findViewById<EditText>(R.id.etNombreColegio)
        val etNro = dialogView.findViewById<EditText>(R.id.etNro)
        val btnConfirmar = dialogView.findViewById<Button>(R.id.btnConfirmar)

        btnConfirmar.setOnClickListener {
            if (etName.text.isNotEmpty() && etNro.text.isNotEmpty()) {
                viewModelColegios.insertColegio(
                    ColegioModel(
                        nombre = etName.text.toString().uppercase(),
                        nro = etNro.text.toString().toInt()
                    )
                )
                dialog.dismiss()
                viewModelColegios.getAllColegiosConCursosConEstudiantes()
                setupIU()
            } else {
                context?.showToast("Debe completar todos los campos")
            }
        }
        dialog.show()
    }

    private fun onEstudianteSelected(cursoId: Int) {
        findNavController().navigate(R.id.action_navigation_colegios_to_navigation_estudiantes)
    }

    private fun onCursoSelected(colegioId: Int) {
        findNavController().navigate(R.id.action_navigation_colegios_to_navigation_cursos)
    }
}
