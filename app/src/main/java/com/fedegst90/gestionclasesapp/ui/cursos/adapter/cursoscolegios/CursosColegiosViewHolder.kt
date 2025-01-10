package com.fedegst90.gestionclasesapp.ui.cursos.adapter.cursoscolegios

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.databinding.ItemCursosBinding
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosYEstudiantesModel
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel
import com.fedegst90.gestionclasesapp.ui.cursos.adapter.cusos.CursosAdapter

class CursosColegiosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemCursosBinding.bind(view)

    fun parse(colegiosItem: ColegioConCursosYEstudiantesModel) {
        binding.tvTitle.text = colegiosItem.colegio.nombre + " N°"+colegiosItem.colegio.nro
        binding.rvCursos.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
        binding.rvCursos.adapter = CursosAdapter(colegiosItem.cursos)
    }
}