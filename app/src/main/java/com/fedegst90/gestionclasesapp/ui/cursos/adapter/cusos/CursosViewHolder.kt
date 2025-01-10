package com.fedegst90.gestionclasesapp.ui.cursos.adapter.cusos

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.databinding.ItemCursosMateriasBinding
import com.fedegst90.gestionclasesapp.domine.model.CursoConEstudiantesModel

class CursosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemCursosMateriasBinding.bind(view)

    fun parse(cursosModel: CursoConEstudiantesModel) {

        binding.tvCursos.text = cursosModel.curso.division
        binding.tvMateria.text = cursosModel.curso.year

    }
}