package com.fedegst90.gestionclasesapp.ui.colegios.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.R
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel

class ColegiosAdapter(
    private var colegiosList: List<ColegioModel>
) : RecyclerView.Adapter<ColegiosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColegiosViewHolder {
        return ColegiosViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_colegio, parent, false)
        )
    }

    override fun getItemCount(): Int = colegiosList.size

    override fun onBindViewHolder(holder: ColegiosViewHolder, position: Int) {
        val item = colegiosList[position]
        holder.parse(item)
    }
}