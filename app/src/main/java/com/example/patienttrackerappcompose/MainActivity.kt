package com.example.patienttrackerappcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.patienttrackerappcompose.ui.theme.PatientTrackerAppComposeTheme
import com.example.patienttrackerappcompose.uiux.PatientCreate
import com.example.patienttrackerappcompose.uiux.PatientRead
import com.example.patienttrackerappcompose.uiux.PatientSearch
import com.example.patienttrackerappcompose.uiux.PatientUpdate
import dagger.hilt.android.AndroidEntryPoint

@Suppress("UNREACHABLE_CODE", "NAME_SHADOWING")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController= rememberNavController()
            var isSearching by remember {
                mutableStateOf(false)
            }
            var query by remember {
                mutableStateOf("")
            }
            val keyboardController= LocalSoftwareKeyboardController.current
            Scaffold(
                topBar = {
                    if (isSearching){
                        TopAppBar(
                            title = {
                                OutlinedTextField(
                                    value= query,
                                    onValueChange = {
                                        query=it
                                    },
                                    placeholder = { Text("Search Here") },
                                    label = {Text("Search Here")},
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Search
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onSearch = {
                                            navController.navigate("Search/$query")
                                            keyboardController?.hide()
                                        }
                                    ),
                                )
                            },
                            actions = {
                                IconButton(onClick = {isSearching=false}) {
                                    Icon(Icons.Default.Close,null)
                                }
                            },
                        )
                    }
                    else{
                        TopAppBar(
                            title = { Text("Patient Trackers By Tento") },
                            actions = {
                                IconButton(onClick = {isSearching=true}) {
                                    Icon(Icons.Default.Search,null)
                                }
                            },
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {navController.navigate("Create")},
                        containerColor = Color.Green,
                        contentColor = Color.White) {
                        Icon(Icons.Default.Add,null)
                    }
                }
            ) {
                NavHost(navController=navController, startDestination = "Read"){
                    composable(route = "Read"){
                        PatientRead(navController)
                    }
                    composable(route = "Create"){
                        PatientCreate(navController)
                    }
                    composable(route = "Update/{idPatient}",
                        arguments = listOf(navArgument("idPatient",{
                            type= NavType.StringType
                        }))
                    ){
                        val idPatient=it.arguments?.getString("idPatient")
                        if (idPatient != null) {
                            PatientUpdate(navController,idPatient)
                        }
                    }
                    composable(route = "Search/{query}",
                        arguments = listOf(navArgument("query",{
                            type= NavType.StringType
                        }))
                    ){
                        val query=it.arguments?.getString("query")
                        if (query != null) {
                            PatientSearch(navController,query)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PatientTrackerAppComposeTheme {
        Greeting("Android")
    }
}