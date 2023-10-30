package com.example.practicarecyclerviewfrancisco.ui.detalle

import com.example.practicarecyclerviewfrancisco.domain.model.FichaMascota
import java.time.LocalDate

data class DetalleFichaState(
    val fichaMascota: FichaMascota = FichaMascota(0, "", "", "", "", "", false, false, 0.0f, "", LocalDate.now()),
    val mensaje: String? = null
)
