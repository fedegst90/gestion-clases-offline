package com.fedegst90.gestionclasesapp.di

import com.fedegst90.gestionclasesapp.data.ColegioRepositoryImpl
import com.fedegst90.gestionclasesapp.data.EstudianteRepositoryImpl
import com.fedegst90.gestionclasesapp.data.dao.ColegioDao
import com.fedegst90.gestionclasesapp.data.dao.EstudianteDao
import com.fedegst90.gestionclasesapp.domine.ColegioRepository
import com.fedegst90.gestionclasesapp.domine.EstudianteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object provideRepository {
    @Provides
    @Singleton
    fun provideEstudianteRepository(
        estudianteDao: EstudianteDao,
    ): EstudianteRepository {
        return EstudianteRepositoryImpl(
            estudianteDao
        )
    }

    @Provides
    @Singleton
    fun provideColegioRepository(
        colegioDao: ColegioDao
    ): ColegioRepository {
        return ColegioRepositoryImpl(
            colegioDao
        )
    }

}