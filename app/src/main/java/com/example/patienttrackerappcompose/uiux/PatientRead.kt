package com.example.patienttrackerappcompose.uiux

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.patienttrackerappcompose.db.PatientViewModel
import com.example.patienttrackerappcompose.ui.theme.PatientTrackerAppComposeTheme

@Composable
fun PatientRead(navController: NavController,viewModel: PatientViewModel= hiltViewModel()) {
    val getPatient by viewModel.getPatient.collectAsState()
    viewModel.getPatient()

    val context= LocalContext.current

    LazyColumn(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 100.dp)) {
        items(getPatient){ item->
            PatientReadCard(
                patient = item.name,
                assignedDoctor = item.doctorAssigned,
                gender = item.gender,
                age = item.age,
                onUpdate = {
                    navController.navigate("Update/${item.idPatient}")
                    viewModel.updatePatient(item)
                },
                onDelete = {
                    viewModel.deletePatient(item.idPatient)
                    Toast.makeText(context,"Item Deleted",Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}

@Composable
fun PatientReadCard(patient:String,gender:String,age:String, assignedDoctor:String, onUpdate:()->Unit, onDelete: ()-> Unit) {
    var isOpened by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier.padding(16.dp)
        .fillMaxWidth(),
        shape=RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        ) {
        Column(modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Text(patient, modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 22.sp)
                IconButton(onClick = {onUpdate()}) {
                    Icon(imageVector = Icons.Default.Edit,contentDescription = null, modifier = Modifier.size(24.dp))
                }
                IconButton(onClick = {isOpened=true}) {
                    Icon(imageVector = Icons.Default.Delete,contentDescription = null, modifier = Modifier.size(24.dp))
                }
                if (isOpened){
                    AlertDialog(
                        onDismissRequest = {isOpened=false},
                        title = { Text("Warning") },
                        text = { Text("Are you sure want to delete this?") },
                        confirmButton = {
                            TextButton(onClick = {isOpened=false
                            onDelete()}) {
                                Text("Confirm")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {isOpened=false}) {
                                Text("Cancel")
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Gender:$gender")
            Text("Age:$age")
            Text("Assigned by $assignedDoctor", fontSize = 16.sp)
        }
    }

}

@Composable
@Preview(showBackground = true)
fun PatientReadPreview(){
    PatientTrackerAppComposeTheme {
        PatientReadCard("dssd","sdsd","sa","as",{},{})
    }
}