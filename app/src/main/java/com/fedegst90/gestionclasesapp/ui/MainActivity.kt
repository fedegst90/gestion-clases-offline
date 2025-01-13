package com.fedegst90.gestionclasesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.fedegst90.gestionclasesapp.R
import com.fedegst90.gestionclasesapp.databinding.ActivityMainBinding
import com.fedegst90.gestionclasesapp.ui.colegios.ColegiosFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHost.navController
        binding.navView.setupWithNavController(navController)



        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_estudiantes -> {
                    if (navController.currentDestination?.id != R.id.navigation_estudiantes) {
                        navController.navigate(
                            R.id.navigation_estudiantes, null,
                            optionNavegation()
                        )
                    }
                    true
                }

                R.id.navigation_colegios -> {
                    if (navController.currentDestination?.id != R.id.navigation_colegios) {
                        navController.navigate(R.id.navigation_colegios, null,optionNavegation())
                    }
                    true
                }

                R.id.navigation_cursos -> {
                    if (navController.currentDestination?.id != R.id.navigation_cursos) {
                        navController.navigate(R.id.navigation_cursos, null,optionNavegation())
                    }
                    true
                }

                else -> false
            }
        }

    }

    private fun optionNavegation() = NavOptions.Builder()
        .setEnterAnim(R.anim.fragment_enter)
        .setExitAnim(R.anim.fragment_exit)
        .setPopEnterAnim(R.anim.fragment_pop_enter)
        .setPopExitAnim(R.anim.fragment_pop_exit)
        .build()
}