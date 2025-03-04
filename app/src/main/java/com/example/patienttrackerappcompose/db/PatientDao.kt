package com.example.patienttrackerappcompose.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.patienttrackerappcompose.model.Patient
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
    @Insert
    fun addPatient(patient: Patient)

    @Query("SELECT * FROM patientDB")
    fun getPatient():Flow<List<Patient>>

    @Query("SELECT * FROM patientDB WHERE idPatient=:idPatient")
    fun getPatientId(idPatient: String):Flow<Patient>

    @Query("SELECT * FROM patientDB WHERE name LIKE :query OR doctorAssigned LIKE:query")
    fun searchPatient(query:String):Flow<List<Patient>>

    @Update
    fun updatePatient(patient: Patient)

    @Query("DELETE FROM patientDB WHERE idPatient=:idPatient")
    fun deletePatient(idPatient: String)
}
