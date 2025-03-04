package com.example.patienttrackerappcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "patientDB")
data class Patient(
    @PrimaryKey
    @NotNull
    var idPatient:String,var name:String,var age:String,var gender:String,
    var prescription:String,var doctorAssigned:String) {
}