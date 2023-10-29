package com.example.practicarecyclerviewfrancisco.data.model

import android.os.Parcelable
import com.example.practicarecyclerviewfrancisco.data.ConstantesData
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class FichaMascota(
    val id: Int,
    @Json(name = ConstantesData.NOMBRE_JSON)
    val propietario: String,
    val email: String,
    val telefono: String,
    @Json(name = ConstantesData.NOMBRE_MASCOTA_JSON)
    val nombreMascota: String,
    @Json(name = ConstantesData.TIPO_MASCOTA_JSON)
    val especie: String,
    val esterilizado: Boolean = false,
    val vacunado: Boolean = false,
    val comportamiento: Float = 0.0f,
    val imagen: String
): Parcelable