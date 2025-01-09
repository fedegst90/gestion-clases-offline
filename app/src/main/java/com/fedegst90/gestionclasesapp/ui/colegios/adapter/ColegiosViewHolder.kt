package com.fedegst90.gestionclasesapp.ui.colegios.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.core.makeGone
import com.fedegst90.gestionclasesapp.data.database.entity.ColegioConCursos
import com.fedegst90.gestionclasesapp.databinding.ItemColegioBinding
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel

class ColegiosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemColegioBinding.bind(view)

    fun parse(
        colegioItem: ColegioConCursos,
        onCursoSelected: (Int) -> Unit,
        onEstudianteSelected: (Int) -> Unit
    ) {
        binding.tvTitle.makeGone()
        binding.tvCantidadColegios.makeGone()
        binding.tvColegios.text = colegioItem.colegio.nombre + " N° " + colegioItem.colegio.nro
        binding.tvCantidadCursos.text = colegioItem.cursos.size.toString()
        binding.imgCursos.setOnClickListener {
            onCursoSelected(
                colegioItem.colegio.id
            )
        }
        binding.imgEstudiantes.setOnClickListener {
            onEstudianteSelected(
            colegioItem.colegio.id
            )
        }

    }
}