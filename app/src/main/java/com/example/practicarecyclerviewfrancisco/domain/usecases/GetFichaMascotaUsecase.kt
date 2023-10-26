package com.example.practicarecyclerviewfrancisco.domain.usecases

import com.example.practicarecyclerviewfrancisco.data.FichaMascotaRepository
import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota

class GetFichaMascotaUsecase (private val repository: FichaMascotaRepository) {
    fun execute(id : Int): FichaMascota{
        return repository.getFicha(id)
    }
}