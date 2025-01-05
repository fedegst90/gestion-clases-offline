package com.fedegst90.gestionclasesapp.ui.colegios.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.databinding.ItemColegioBinding
import com.fedegst90.gestionclasesapp.domine.model.ColegioModel

class ColegiosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemColegioBinding.bind(view)

    fun parse(colegioItem:ColegioModel){
        binding.tvColegios.text = colegioItem.nombre
    }
}