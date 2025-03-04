package com.example.patienttrackerappcompose.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.patienttrackerappcompose.model.Patient

@Database(entities = [Patient::class], version = 1)
abstract class PatientDB:RoomDatabase() {
    abstract fun patientDao():PatientDao
}