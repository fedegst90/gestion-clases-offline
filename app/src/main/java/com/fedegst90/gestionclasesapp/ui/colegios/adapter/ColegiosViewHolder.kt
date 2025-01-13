package com.fedegst90.gestionclasesapp.ui.colegios.adapter

import android.graphics.Color
import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.R
import com.fedegst90.gestionclasesapp.core.disable
import com.fedegst90.gestionclasesapp.core.disableViews
import com.fedegst90.gestionclasesapp.core.enable
import com.fedegst90.gestionclasesapp.core.enableViews
import com.fedegst90.gestionclasesapp.core.makeGone
import com.fedegst90.gestionclasesapp.core.makeVisible
import com.fedegst90.gestionclasesapp.databinding.ItemColegioBinding
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosYEstudiantesModel

class ColegiosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemColegioBinding.bind(view)

    fun parse(
        colegioItem: ColegioConCursosYEstudiantesModel,
        onCursoSelected: (Int) -> Unit,
        onEstudianteSelected: (Int) -> Unit,
        onItemDelete: (Int) -> Unit
    ) {
        binding.root.setOnLongClickListener {
            colegioItem.isSelected = !colegioItem.isSelected
            if (colegioItem.isSelected) {
                binding.cLayout.disable()
                binding.root.setBackgroundColor(Color.LTGRAY)
                binding.imgDelete.apply {
                makeVisible()
                enable()
                }

            } else {
                binding.imgDelete.makeGone()
                binding.root.setBackgroundColor(Color.WHITE)
            }
            true
        }


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
        binding.imgDelete.setOnClickListener {
            onItemDelete(colegioItem.colegio.id)
        }

    }
}