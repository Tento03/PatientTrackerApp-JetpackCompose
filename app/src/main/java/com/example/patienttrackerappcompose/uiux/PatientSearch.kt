package com.example.patienttrackerappcompose.uiux

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.patienttrackerappcompose.db.PatientViewModel
import com.example.patienttrackerappcompose.ui.theme.PatientTrackerAppComposeTheme

@Composable
fun PatientSearch(navController: NavController,query:String,viewModel: PatientViewModel= hiltViewModel()) {
    val searchPatient by viewModel.searchPatient.collectAsState()
    viewModel.searchPatient(query)

    LazyColumn(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 100.dp)) {
        items(searchPatient){ item->
            PatientSearchCard(
                patient = item.name,
                assignedDoctor = item.doctorAssigned,
                onUpdate = {},
                onDelete = {}
            )
        }
    }

}

@Composable
fun PatientSearchCard(patient:String,assignedDoctor:String,onUpdate:()->Unit,onDelete:()->Unit) {
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
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Edit,contentDescription = null, modifier = Modifier.size(24.dp))
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Delete,contentDescription = null, modifier = Modifier.size(24.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Assigned by $assignedDoctor", fontSize = 16.sp)
        }

    }

}

@Composable
@Preview(showBackground = true)
fun PatientSearchPreview(){
    PatientTrackerAppComposeTheme {
        PatientReadCard("dssd","sdsd","s","a",{},{})
    }
}