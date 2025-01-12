package com.fedegst90.gestionclasesapp.ui.cursos.adapter.cursoscolegios

import android.graphics.Typeface
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fedegst90.gestionclasesapp.databinding.ItemCursosBinding
import com.fedegst90.gestionclasesapp.domine.model.ColegioConCursosYEstudiantesModel
import com.fedegst90.gestionclasesapp.ui.cursos.adapter.cusos.CursosAdapter

class CursosColegiosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemCursosBinding.bind(view)

    fun parse(
        colegiosItem: ColegioConCursosYEstudiantesModel,
        onAddCursoSelected: (Int) -> Unit
    ) {
        binding.tvTitle.apply {
            text = "${colegiosItem.colegio.nombre} N°${colegiosItem.colegio.nro}"
            setTypeface(typeface, Typeface.BOLD)
        }

        val cursosAdapter = CursosAdapter(colegiosItem.cursos)

        binding.rvCursos.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = cursosAdapter
            post {
                val itemCount =
                    cursosAdapter.itemCount
                if (itemCount > 0) {
                    val scrollStep = 20L
                    val smoothScrollHandler = Handler(Looper.getMainLooper())
                    var currentPosition = 0

                    val runnable = object : Runnable {
                        override fun run() {
                            if (currentPosition < itemCount) {
                                binding.rvCursos.smoothScrollToPosition(currentPosition)
                                currentPosition++
                                smoothScrollHandler.postDelayed(this, scrollStep)
                            }
                        }
                    }

                    smoothScrollHandler.post(runnable)
                }
            }
        }

        binding.btnAddCurso.setOnClickListener {
            onAddCursoSelected(colegiosItem.colegio.id)
        }
    }
}
