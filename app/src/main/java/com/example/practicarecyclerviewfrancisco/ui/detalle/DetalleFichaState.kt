package com.example.practicarecyclerviewfrancisco.ui.detalle

import com.example.practicarecyclerviewfrancisco.domain.model.FichaMascota

data class DetalleFichaState(
    val fichaMascota: FichaMascota = FichaMascota(0, "", "", "", "", "", false, false, 0.0f, ""),
    val mensaje: String? = null
)
