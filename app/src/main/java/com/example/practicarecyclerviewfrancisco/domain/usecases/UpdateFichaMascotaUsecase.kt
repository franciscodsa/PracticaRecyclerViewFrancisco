package com.example.practicarecyclerviewfrancisco.domain.usecases

import com.example.practicarecyclerviewfrancisco.data.FichaMascotaRepository
import com.example.practicarecyclerviewfrancisco.domain.model.FichaMascota

class UpdateFichaMascotaUsecase(private val repository: FichaMascotaRepository) {
    fun execute(fichaMascotaUpdated : FichaMascota){
        repository.updateFicha(fichaMascotaUpdated)
    }
}