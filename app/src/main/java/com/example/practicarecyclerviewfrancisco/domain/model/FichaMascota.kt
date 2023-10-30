package com.example.practicarecyclerviewfrancisco.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class FichaMascota(
    val id: Int,
    @Json(name = Constantes.NOMBRE_JSON)
    val propietario: String,
    val email: String,
    val telefono: String,
    @Json(name = Constantes.NOMBRE_MASCOTA_JSON)
    val nombreMascota: String,
    @Json(name = Constantes.TIPO_MASCOTA_JSON)
    val especie: String,
    val esterilizado: Boolean = false,
    val vacunado: Boolean = false,
    val comportamiento: Float = 0.0f,
    val imagen: String,
    val fecha: LocalDate
): Parcelable