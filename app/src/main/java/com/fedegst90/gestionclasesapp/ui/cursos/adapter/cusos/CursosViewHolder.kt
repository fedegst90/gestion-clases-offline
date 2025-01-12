package com.fedegst90.gestionclasesapp.ui.cursos.adapter.cusos

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.databinding.ItemCursosMateriasBinding
import com.fedegst90.gestionclasesapp.domine.model.CursoConEstudiantesModel

class CursosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemCursosMateriasBinding.bind(view)

    fun parse(cursosModel: CursoConEstudiantesModel) {
        binding.apply {
            tvCursos.text = cursosModel.curso.year +" "+cursosModel.curso.division
            tvMateria.text = cursosModel.curso.materia.uppercase()
            circuleCetral.background.setTint(Color.parseColor(cursosModel.curso.color))
        }
    }
}