package com.example.practicarecyclerviewfrancisco.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class FichaMascota(
    val id: Int,
    @Json(name = "nombre")
    val propietario: String,
    val email: String,
    val telefono: String,
    @Json(name = "nombre_mascota")
    val nombreMascota: String,
    @Json(name = "tipo_mascota")
    val especie: String,
    val esterilizado: Boolean = false,
    val vacunado: Boolean = false,
    val comportamiento: Float = 0.0f
): Parcelable