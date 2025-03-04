package com.example.patienttrackerappcompose.db

import androidx.room.Dao
import com.example.patienttrackerappcompose.model.Patient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PatientRepository @Inject constructor(private var dao: PatientDao) {
    suspend fun addPatient(patient: Patient){
        return dao.addPatient(patient)
    }
    suspend fun getPatient(): Flow<List<Patient>>{
        return dao.getPatient()
    }
    suspend fun getPatientId(id:String):Flow<Patient>{
        return dao.getPatientId(id)
    }
    suspend fun searchPatient(query:String):Flow<List<Patient>>{
        var searchQuery="%$query%"
        return dao.searchPatient(searchQuery)
    }
    suspend fun updatePatient(patient: Patient){
        return dao.updatePatient(patient)
    }
    suspend fun deletePatient(id: String){
        return dao.deletePatient(id)
    }
}