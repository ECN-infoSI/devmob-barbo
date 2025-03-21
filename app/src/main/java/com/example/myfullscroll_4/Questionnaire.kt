package com.example.myfullscroll_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown

class Questionnaire : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val buttonName = intent.getStringExtra("button_name") ?: "Personnalisé"
        setContent {
            MaterialTheme {
                FormulaireSelonBouton(buttonName)
            }
        }
    }
}

@Composable
fun FormulaireSelonBouton(buttonName: String) {
    val defaultTemps = when (buttonName) {
        "Etudiant" -> "15min"
        "Travailleur" -> "20min"
        "Detente" -> "10min"
        else -> "10min"
    }
    val defaultScrollMax = when (buttonName) {
        "Etudiant" -> "20"
        "Travailleur" -> "30"
        "Detente" -> "25"
        else -> "20"
    }

    var tempsScrolling by remember { mutableStateOf(defaultTemps) }
    var jourRepos by remember { mutableStateOf("Aucun") }
    var typeRestriction by remember { mutableStateOf("Notification") }
    var heureDebut by remember { mutableStateOf("Pas d'heure de restriction") }
    var heureFin by remember { mutableStateOf("Pas d'heure de restriction") }
    var scrollMax by remember { mutableStateOf(defaultScrollMax) }
    var accepted by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = "Profil: $buttonName", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = tempsScrolling,
            onValueChange = { tempsScrolling = it },
            label = { Text("Temps de scrolling par jour") },
            singleLine = true
        )

        DropdownField(label = "Jour de repos", options = listOf("Aucun","lundi","mardi","mercredi","jeudi","vendredi", "Dimanche", "Samedi"), selected = jourRepos) { jourRepos = it }
        DropdownField(label = "Type de restriction", options = listOf("Notification", "Blocage","pop up"), selected = typeRestriction) { typeRestriction = it }
        DropdownField(label = "Heure début restriction", options = listOf("Pas d'heure de restriction", "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"), selected = heureDebut) { heureDebut = it }
        DropdownField(label = "Heure fin restriction", options = listOf("Pas d'heure de restriction", "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"), selected = heureFin) { heureFin = it }

        OutlinedTextField(
            value = scrollMax,
            onValueChange = { scrollMax = it },
            label = { Text("Nombre de scroll max") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = accepted, onCheckedChange = { accepted = it })
            Text("J'accepte les conditions d'utilisation")
        }

        Button(
            onClick = { /* Action après validation */ },
            enabled = accepted,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Commencer à utiliser mindfulscroll")
        }
    }
}

@Composable
fun DropdownField(label: String, options: List<String>, selected: String, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(text = { Text(option) }, onClick = {
                    onSelect(option)
                    expanded = false
                })
            }
        }
    }
}
