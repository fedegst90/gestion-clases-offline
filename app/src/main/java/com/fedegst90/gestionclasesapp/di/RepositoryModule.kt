package com.fedegst90.gestionclasesapp.di

import com.fedegst90.gestionclasesapp.data.RepositoryImpl
import com.fedegst90.gestionclasesapp.domine.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(

    ): Repository {
        return RepositoryImpl(

        )
    }

}