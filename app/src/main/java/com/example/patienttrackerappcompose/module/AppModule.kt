package com.example.patienttrackerappcompose.module

import android.content.Context
import androidx.room.Room
import com.example.patienttrackerappcompose.db.PatientDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesPatientDB(@ApplicationContext app:Context):PatientDB=Room.databaseBuilder(
        app.applicationContext,PatientDB::class.java,"PatientDBCompose"
    ).build()

    @Singleton
    @Provides
    fun providesPatientDao(db: PatientDB)=db.patientDao()

}