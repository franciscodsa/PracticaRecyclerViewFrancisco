package com.example.practicarecyclerviewfrancisco.data

import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota

class FichaMascotaRepository {
    companion object{
        val fichaMascotaList = listOf<FichaMascota>(
            FichaMascota(
                "Juan",
                "juan@mail.com",
                "87646413",
                "Max",
                "Perro",
                false,
                true,
                5f
            ),
            FichaMascota(
                "Alejandro",
                "ale@mail.com",
                "65432101",
                "Simba",
                "Gato",
                true,
                true,
                8.5f
            ),
            FichaMascota(
                "Pepe",
                "pepe@mail.com",
                "648325432",
                "Luna",
                "Gato",
                false,
                false,
                2f
            )
        )
    }

}