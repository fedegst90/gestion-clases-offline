package com.fedegst90.gestionclasesapp.di

import android.content.Context
import androidx.room.Room
import com.fedegst90.gestionclasesapp.data.DataBaseApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    companion object {
        private const val DATA_BASE_NAME = "app_datebase"
    }

    @Singleton
    @Provides
    fun provideRoomEstudiante(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DataBaseApp::class.java, DATA_BASE_NAME)
            .build()

    @Singleton
    @Provides
    fun provideUsuarioDao(db: DataBaseApp) = db.getEstudienteDao()

    @Singleton
    @Provides
    fun provideUsuarioRecordarDao(db: DataBaseApp) = db.getColegioDao()

}