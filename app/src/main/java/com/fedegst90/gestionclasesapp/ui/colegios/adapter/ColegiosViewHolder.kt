package com.fedegst90.gestionclasesapp.ui.colegios.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.core.makeGone
import com.fedegst90.gestionclasesapp.databinding.ItemColegioBinding
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosYEstudiantesModel

class ColegiosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemColegioBinding.bind(view)

    fun parse(
        colegioItem: ColegioConCursosYEstudiantesModel,
        onCursoSelected: (Int) -> Unit,
        onEstudianteSelected: (Int) -> Unit
    ) {
        binding.tvTitle.makeGone()
        binding.tvCantidadColegios.makeGone()
        binding.tvColegios.text = colegioItem.colegio.nombre + " N° " + colegioItem.colegio.nro
        binding.tvCantidadCursos.text = colegioItem.cursos.size.toString()
        binding.tvCantidadEstudiantes.text =
            colegioItem.cursos.sumOf { it.estudiantes.size }.toString()
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