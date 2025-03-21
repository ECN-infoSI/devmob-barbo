package com.example.myfullscroll_4

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfullscroll_4.ui.theme.MyFullScroll_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFullScroll_4Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        MainScreen(modifier = Modifier.padding(innerPadding))
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val intent = Intent(context, Questionnaire::class.java)
    intent.putExtra("button_name", "Personnalisé")





    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                intent.putExtra("button_name", "Etudiant")
                context.startActivity(intent) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Etudiant")
        }

        Button(
            onClick = {
                intent.putExtra("button_name", "Travailleur")
                context.startActivity(intent) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Travailleur")
        }

        Button(
            onClick = {
                intent.putExtra("button_name", "Detente")
                context.startActivity(intent) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Detente")
        }

        Button(

            onClick = {
                intent.putExtra("button_name", "Personnalisé")
                context.startActivity(intent) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Personnalisé")
        }
    }
}

// Function to handle navigation to SecondActivity
//fun navigateToSecondActivity() {
//    val intent = Intent(context, SecondActivity::class.java)
//    context.startActivity(intent)
//}





@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MyFullScroll_4Theme {
        MainScreen()
    }
}

