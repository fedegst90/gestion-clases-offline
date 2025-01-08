package com.fedegst90.gestionclasesapp.core

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun makeViewsVisible(vararg views: View) {
    views.forEach { it.visibility = View.VISIBLE }
}

fun makeViewsGone(vararg views: View) {
    views.forEach { it.visibility = View.GONE }
}

fun makeViewsInvisible(vararg views: View) {
    views.forEach { it.visibility = View.INVISIBLE }
}

fun View.enable() {
    this.isEnabled = true
    this.alpha = 1f
}

fun View.disable() {
    this.isEnabled = false
    this.alpha = 0.5f
}

fun enableViews(vararg views: View) {
    views.forEach { it.enable() }
}

fun disableViews(vararg views: View) {
    views.forEach { it.disable() }
}

fun View.esconderTeclado() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.mostrarTeclado() {
    val imm =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun EditText.setEnterKeyListener(onEnterPressed: () -> Unit) {
    this.setOnKeyListener { _, keyCode, event ->
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            onEnterPressed()
            true
        } else {
            false
        }
    }
}