package com.ulisesg.admingolazo.freatures.products.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ulisesg.admingolazo.freatures.products.domain.entities.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(
    product: Product? = null,
    onSave: (Product) -> Unit,
    onDelete: (Product) -> Unit,
    onBack: () -> Unit
) {
    var nombre by remember { mutableStateOf(product?.nombre ?: "") }
    var precio by remember { mutableStateOf(product?.precio?.toString() ?: "") }
    var descripcion by remember { mutableStateOf(product?.descripcion ?: "") }
    var estaActivo by remember { mutableStateOf(product?.estaActivo ?: true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (product == null) "Nuevo Producto" else "Editar Producto") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    if (product != null) {
                        IconButton(onClick = { onDelete(product) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = estaActivo, onCheckedChange = { estaActivo = it })
                Text("Producto Activo")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    val newProduct = Product(
                        id = product?.id ?: "",
                        nombre = nombre,
                        precio = precio.toIntOrNull() ?: 0,
                        descripcion = descripcion,
                        imagenes = product?.imagenes ?: emptyList(),
                        esDestacado = product?.esDestacado ?: false,
                        estaActivo = estaActivo,
                        fecha_creacion = product?.fecha_creacion ?: "",
                        categoria_id = if (product == null) 1 else product.categoria_id
                    )
                    onSave(newProduct)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = nombre.isNotBlank() && precio.isNotBlank()
            ) {
                Text("Guardar")
            }
        }
    }
}
