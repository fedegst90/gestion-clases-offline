package com.fedegst90.gestionclasesapp.ui.cursos.adapter.cusos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.R
import com.fedegst90.gestionclasesapp.domine.model.CursoConEstudiantesModel

class CursosAdapter(
    private val listCursosModel: List<CursoConEstudiantesModel>
) : RecyclerView.Adapter<CursosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursosViewHolder {
        return CursosViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cursos_materias, parent, false)
        )
    }

    override fun getItemCount(): Int = listCursosModel.size

    override fun onBindViewHolder(holder: CursosViewHolder, position: Int) {
        val item = listCursosModel[position]
        holder.parse(item)
    }
}