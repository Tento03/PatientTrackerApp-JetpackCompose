package com.example.patienttrackerappcompose.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patienttrackerappcompose.model.Patient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(private var repository: PatientRepository):ViewModel() {
    private val _getPatient = MutableStateFlow<List<Patient>>(emptyList())
    var getPatient:MutableStateFlow<List<Patient>> =_getPatient

    private val _getPatientId = MutableStateFlow<Patient?>(null)
    var getPatientId:MutableStateFlow<Patient?> = _getPatientId

    private val _searchPatient=MutableStateFlow<List<Patient>>(emptyList())
    var searchPatient:MutableStateFlow<List<Patient>> = _searchPatient

    fun addPatient(patient: Patient){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addPatient(patient)
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun getPatient(){
        try {
            viewModelScope.launch {
                var response=repository.getPatient().collect{
                    _getPatient.value=it
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun getPatientId(id:String){
        try {
            viewModelScope.launch {
                var response=repository.getPatientId(id).collect{
                    _getPatientId.value=it
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun searchPatient(id: String){
        try {
            viewModelScope.launch {
                var response=repository.searchPatient(id).collect{
                    _searchPatient.value=it
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun updatePatient(patient: Patient){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.updatePatient(patient)
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun deletePatient(id:String){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deletePatient(id)
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
}