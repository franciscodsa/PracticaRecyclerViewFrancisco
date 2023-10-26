package com.example.practicarecyclerviewfrancisco.domain.usecases

import com.example.practicarecyclerviewfrancisco.data.FichaMascotaRepository
import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota

class AddFichaMascotaUsecase(private val repository: FichaMascotaRepository) {
    fun execute(fichaMascota: FichaMascota){
        repository.addFicha(fichaMascota)
    }
}