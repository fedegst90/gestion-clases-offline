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
import androidx.recyclerview.widget.LinearLayoutManager
import com.fedegst90.gestionclasesapp.R

import com.fedegst90.gestionclasesapp.core.showToast
import com.fedegst90.gestionclasesapp.databinding.FragmentColegiosBinding
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel
import com.fedegst90.gestionclasesapp.ui.colegios.adapter.ColegiosAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColegiosFragment : Fragment() {

    private var _binding: FragmentColegiosBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ColegiosViewModel by viewModels()
    private lateinit var adapterColegio: ColegiosAdapter
    private lateinit var popupAdapter: ArrayAdapter<String>
    private lateinit var listPopupWindow: ListPopupWindow
    private var listColegioModel: List<ColegioModel> = emptyList()

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

        viewModel.getAllColegios()
        setupListener()
        setupObserver()
       // setupSearchView()
        //setupPopup()
    }

    private fun setupObserver() {
        viewModel.colegios.observe(viewLifecycleOwner) {
           /* if (it.isEmpty()) {
                adapterColegio.updateList(listOf())
            } else {
<<<<<<< HEAD

            */
                Log.d("ColegiosFragment", "Colegios recibidos: ${it}")
                listColegioModel=it
=======
                listColegioModel = it
>>>>>>> origin/IU
                adapterColegio.updateList(it)

            //}
        }
    }

    private fun setupListener() {
        binding.btnAddColegio.setOnClickListener {
            dialogNewColegio()
        }
    }

    private fun setupRecyclerView() {
        adapterColegio = ColegiosAdapter()
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
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            listColegioModel.toMutableList().map { it.nombre })
        listPopupWindow.setAdapter(popupAdapter)


        listPopupWindow.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = popupAdapter.getItem(position)
            binding.searchView.setQuery(selectedItem.toString(), false)
            listPopupWindow.dismiss()
        }
    }

    private fun updatePopupAdapter(filterList: List<ColegioModel>) {
        popupAdapter.clear()
        popupAdapter.addAll(filterList.map { it.nombre })
        if (filterList.isNotEmpty()) {
            listPopupWindow.show()
        } else {
            listPopupWindow.dismiss()
        }
    }

    private fun filterItems(query: String): List<ColegioModel> =
        listColegioModel.filter { it.nombre.contains(query, ignoreCase = true) }

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
                viewModel.insertColegio(
                    ColegioModel(
                        nombre = etName.text.toString().uppercase(),
                        nro = etNro.text.toString().toInt()
                    )
                )

                dialog.dismiss()
                viewModel.getAllColegios()
            } else {
                context?.showToast("Debe completar todos los campos")
            }
        }
        dialog.show()
    }
}
