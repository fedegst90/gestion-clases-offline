package com.fedegst90.gestionclasesapp.ui.cursos.adapter.cursoscolegios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.R
import com.fedegst90.gestionclasesapp.core.GenericDiff
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosYEstudiantesModel

class CursosColegiosAdapter(
    private var listColegiosModel: List<ColegioConCursosYEstudiantesModel> = emptyList(),
    private val onAddCursoSelected: (Int) -> Unit
) : RecyclerView.Adapter<CursosColegiosViewHolder>() {

    fun updateList(newList: List<ColegioConCursosYEstudiantesModel>) {
        if (listColegiosModel.isEmpty()) {
            this.listColegiosModel = newList
            notifyDataSetChanged()
        } else {
            val listDiff = GenericDiff(listColegiosModel, newList, idSelector = { it.colegio.id })
            val result = DiffUtil.calculateDiff(listDiff)
            listColegiosModel = newList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursosColegiosViewHolder {
        return CursosColegiosViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cursos, parent, false)
        )
    }

    override fun getItemCount(): Int = listColegiosModel.size

    override fun onBindViewHolder(holder: CursosColegiosViewHolder, position: Int) {
        val item = listColegiosModel[position]
        holder.parse(item, onAddCursoSelected)
    }
}