package com.example.practicarecyclerviewfrancisco.data

class FichaMascotaRepository {
    companion object{
        val fichaMascotaList = listOf<FichaMascota>(
            FichaMascota(
                "Juan",
                "juan@mail.com",
                "87646413",
                "Max",
                1,
                false,
                true,
                5f
            ),
            FichaMascota(
                "Alejandro",
                "ale@mail.com",
                "65432101",
                "Simba",
                2,
                true,
                true,
                8.5f
            ),
            FichaMascota(
                "Pepe",
                "pepe@mail.com",
                "648325432",
                "Luna",
                2,
                false,
                false,
                2f
            )
        )
    }
}