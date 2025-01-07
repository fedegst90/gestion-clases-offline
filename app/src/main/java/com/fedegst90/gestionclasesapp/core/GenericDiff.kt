package com.fedegst90.gestionclasesapp.core

import androidx.recyclerview.widget.DiffUtil

class GenericDiff<T : Any>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val idSelector: (T) -> Any
) : DiffUtil.Callback(

) {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return idSelector(oldList[oldItemPosition]) == idSelector(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}