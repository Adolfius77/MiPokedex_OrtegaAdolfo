package ortega.adolfo.mipokedex_ortegaadolfo.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.mipokedex_ortegaadolfo.R
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme
import ortega.adolfo.mipokedex_ortegaadolfo.utilities.TipoPokemon
import ortega.adolfo.mipokedex_ortegaadolfo.viewmodel.AddPokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPokemonScreen(
    innerPadding: PaddingValues = PaddingValues(),
    viewModel: AddPokemonViewModel = viewModel(factory = AddPokemonViewModel.Factory)
) {
    val dbPokemons by viewModel.pokemonList.collectAsState()

    var number by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var heightText by remember { mutableStateOf("") }
    var weightText by remember { mutableStateOf("") }
    var ability by remember { mutableStateOf("") }

    val tipoOptions = TipoPokemon.entries
    var selectedTipo by remember { mutableStateOf(tipoOptions.first()) }
    var openTypeDropdown by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Text(
                text = "Agregar Pokémon",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = number,
                onValueChange = { number = it },
                label = { Text("Número Pokédex (ej. #0025)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = heightText,
                onValueChange = { heightText = it },
                label = { Text("Altura (m)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = weightText,
                onValueChange = { weightText = it },
                label = { Text("Peso (kg)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = ability,
                onValueChange = { ability = it },
                label = { Text("Habilidad") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            ExposedDropdownMenuBox(
                expanded = openTypeDropdown,
                onExpandedChange = { openTypeDropdown = !openTypeDropdown },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedTipo.etiqueta,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = openTypeDropdown) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = openTypeDropdown,
                    onDismissRequest = { openTypeDropdown = false }
                ) {
                    tipoOptions.forEach { tipo ->
                        DropdownMenuItem(
                            text = { Text(tipo.etiqueta) },
                            onClick = {
                                selectedTipo = tipo
                                openTypeDropdown = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val numString = number.toString().trim()
                    val nameString = name.toString().trim()
                    val descString = description.toString().trim()
                    val heightVal = heightText.toString().toDoubleOrNull() ?: 0.0
                    val weightVal = weightText.toString().toIntOrNull() ?: 0
                    val abilityString = ability.toString().trim()

                    if (nameString.isNotEmpty() && numString.isNotEmpty()) {
                        val newPokemon = Pokemon(
                            nombre = nameString,
                            Altura = heightVal,
                            Habilidad = abilityString,
                            Peso = weightVal,
                            descripcion = descString,
                            numPokedex = numString,
                            tipo = selectedTipo,
                            imgId = R.drawable.pikachu,
                            favorite = false
                        )
                        viewModel.savePokemon(newPokemon)

                        number = ""
                        name = ""
                        description = ""
                        heightText = ""
                        weightText = ""
                        ability = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Pokémon")
            }

            if (viewModel.isPokemonSaved) {
                Text(
                    text = "¡Pokémon guardado con éxito!",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Pokémon guardados en Base de Datos (${dbPokemons.size}):",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }

        if (dbPokemons.isEmpty()) {
            item {
                Text(
                    text = "No hay pokémones en la base de datos.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            items(dbPokemons) { pokemon ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "${pokemon.numPokedex} - ${pokemon.nombre}",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Tipo: ${pokemon.tipo.etiqueta} | Habilidad: ${pokemon.Habilidad}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "Descripción: ${pokemon.descripcion}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = pokemon.toString(),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun addPokemon(innerPading: PaddingValues = PaddingValues()) {
    AddPokemonScreen(innerPadding = innerPading)
}

@Preview(showBackground = true)
@Composable
fun AddPokemonScreenPreview() {
    PokedexTheme {
        AddPokemonScreen()
    }
}
