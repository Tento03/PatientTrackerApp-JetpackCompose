package com.example.patienttrackerappcompose.uiux

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.patienttrackerappcompose.db.PatientViewModel
import com.example.patienttrackerappcompose.model.Patient
import com.example.patienttrackerappcompose.ui.theme.PatientTrackerAppComposeTheme
import java.util.UUID

@Composable
fun PatientUpdate(navController: NavController,idPatient:String,viewModel: PatientViewModel= hiltViewModel()) {
    val genderList= listOf("Male","Female")
    val keyboardController= LocalSoftwareKeyboardController.current
    val context= LocalContext.current

    val getPatientId by viewModel.getPatientId.collectAsState()
    viewModel.getPatientId(idPatient)

    getPatientId?.let { patient->
        var name by remember {
            mutableStateOf(patient.name)
        }
        var age by remember {
            mutableStateOf(patient.age)
        }
        var gender by remember {
            mutableStateOf(patient.gender)
        }
        var assignedDoctor by remember {
            mutableStateOf(patient.doctorAssigned)
        }
        var prescription by remember {
            mutableStateOf(patient.prescription)
        }

        Column(modifier = Modifier.padding(top = 100.dp, start = 20.dp, end = 20.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name=it
                },
                placeholder = { Text("Patient Name") },
                label = { Text("Patient Name") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = age,
                    onValueChange = {
                        age=it
                    },
                    placeholder = { Text("Age") },
                    label = { Text("Age") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    genderList.forEach { genderOpt->
                        RadioButton(
                            selected = gender==genderOpt,
                            onClick = {
                                gender=genderOpt
                            },
                        )
                        Text(genderOpt)
                    }
                }
            }
            OutlinedTextField(
                value = assignedDoctor,
                onValueChange = {
                    assignedDoctor=it
                },
                placeholder = { Text("Doctor Name") },
                label = { Text("Doctor Name") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = prescription,
                onValueChange = {
                    prescription=it
                },
                placeholder = { Text("Prescription") },
                label = { Text("Prescription") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
            Button(onClick = {
                if (name.isNotEmpty() && age.isNotEmpty() && assignedDoctor.isNotEmpty()
                    &&prescription.isNotEmpty()) {
                    var patient= Patient(idPatient,name,age,gender,prescription,assignedDoctor)
                    viewModel.updatePatient(patient)
                    Toast.makeText(context,"List Added", Toast.LENGTH_SHORT).show()
                    name=""
                    age=""
                    assignedDoctor=""
                    prescription=""
                    navController.navigate("Read")
                }
            }, shape = RoundedCornerShape(0.dp)) {
                Text("Submit", textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.fillMaxWidth()
                )
            }
        }
    } ?:run{
        Text("Loading...", modifier = Modifier.padding(16.dp))
    }


}

//@Preview(showBackground = true)
//@Composable
//private fun PatientUpdatePrev() {
//    PatientTrackerAppComposeTheme {
//        PatientUpdate()
//    }
//}