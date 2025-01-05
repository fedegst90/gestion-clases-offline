package com.fedegst90.gestionclasesapp.ui.colegios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fedegst90.gestionclasesapp.databinding.FragmentColegiosBinding
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel
import com.fedegst90.gestionclasesapp.ui.colegios.adapter.ColegiosAdapter

class ColegiosFragment : Fragment() {

    private var _binding: FragmentColegiosBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ColegiosViewModel by viewModels()
    private lateinit var adapterColegio :ColegiosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentColegiosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listaColegios = listOf(
            ColegioModel(id = 1, nombre = "Colegio A", nro = 100),
            ColegioModel(id = 2, nombre = "Colegio B", nro = 200),
            ColegioModel(id = 3, nombre = "Colegio C", nro = 300),
            ColegioModel(id = 4, nombre = "Colegio D", nro = 400),
            ColegioModel(id = 5, nombre = "Colegio E", nro = 500),
            ColegioModel(id = 6, nombre = "Colegio F", nro = 600),
            ColegioModel(id = 7, nombre = "Colegio G", nro = 700),
            ColegioModel(id = 8, nombre = "Colegio H", nro = 800),
            ColegioModel(id = 9, nombre = "Colegio I", nro = 900),
            ColegioModel(id = 10, nombre = "Colegio J", nro = 1000)
        )


        adapterColegio = ColegiosAdapter(listaColegios)
        binding.rvColegios.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = adapterColegio
        }
    }
}