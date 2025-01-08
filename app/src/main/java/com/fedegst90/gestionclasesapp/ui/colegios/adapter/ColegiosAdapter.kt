package com.fedegst90.gestionclasesapp.ui.colegios.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.R
import com.fedegst90.gestionclasesapp.core.GenericDiff
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel

class ColegiosAdapter(
    private var colegiosList: List<ColegioModel> = emptyList()
) : RecyclerView.Adapter<ColegiosViewHolder>() {

    fun updateList(newList: List<ColegioModel>) {
        val listdiff = GenericDiff(colegiosList, newList, idSelector = { it.id })
        val result = DiffUtil.calculateDiff(listdiff)
        colegiosList = newList

        result.dispatchUpdatesTo(this)
    }

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