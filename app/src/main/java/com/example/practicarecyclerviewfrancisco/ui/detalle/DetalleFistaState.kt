package com.example.practicarecyclerviewfrancisco.ui.detalle

import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota

data class DetalleFistaState (
    val fichaMascota: FichaMascota = FichaMascota("", "", "", "", "", false, false, 0.0f),
    val mensaje: String? = null
)
