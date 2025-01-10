package com.fedegst90.gestionclasesapp.ui.cursos.adapter.cursoscolegios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.R
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosYEstudiantesModel
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel

class CursosColegiosAdapter(
    private val listColegiosModel: List<ColegioConCursosYEstudiantesModel>
) : RecyclerView.Adapter<CursosColegiosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursosColegiosViewHolder {
        return CursosColegiosViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cursos, parent, false)
        )
    }

    override fun getItemCount(): Int = listColegiosModel.size

    override fun onBindViewHolder(holder: CursosColegiosViewHolder, position: Int) {
        val item = listColegiosModel[position]
        holder.parse(item)
    }
}